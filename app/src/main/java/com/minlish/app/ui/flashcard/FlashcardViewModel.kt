package com.minlish.app.ui.flashcard

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.dao.CardDao
import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.dao.ReviewDao
import com.minlish.app.data.local.dao.UserStatsDao
import com.minlish.app.data.local.entity.CardEntity
import com.minlish.app.data.local.entity.LearningLogEntity
import com.minlish.app.data.local.entity.ReviewEntity
import com.minlish.app.data.local.entity.UserStatsEntity
import com.minlish.app.domain.usecase.CalculateSM2IntervalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FlashcardUiState(
    val currentCard: CardEntity? = null,
    val currentReview: ReviewEntity? = null,
    val isFinished: Boolean = false,
    val isLoading: Boolean = true,
    val currentIndex: Int = 0,
    val totalCards: Int = 0
)


@HiltViewModel
class FlashcardViewModel @Inject constructor(
    private val cardDao: CardDao,
    private val reviewDao: ReviewDao,
    private val learningLogDao: LearningLogDao,
    private val userStatsDao: UserStatsDao,
    private val calculateSM2: CalculateSM2IntervalUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlashcardUiState())
    val uiState: StateFlow<FlashcardUiState> = _uiState.asStateFlow()

    private var dueCards: List<ReviewEntity> = emptyList()
    private var currentIndex = 0
    private var mediaPlayer: MediaPlayer? = null

    init {
        loadDueCards()
    }

    private fun loadDueCards() {
        viewModelScope.launch {
            reviewDao.getDueReviews(System.currentTimeMillis()).first().let { reviews ->
                android.util.Log.d("FlashcardVM", "Due cards found: ${reviews.size}")
                dueCards = reviews
                if (dueCards.isNotEmpty()) {
                    _uiState.update { it.copy(totalCards = reviews.size) }
                    showCard(0)
                } else {
                    _uiState.update { it.copy(isLoading = false, isFinished = true) }
                }
            }
        }
    }

    private fun showCard(index: Int) {
        if (index < dueCards.size) {
            viewModelScope.launch {
                val review = dueCards[index]
                android.util.Log.d("FlashcardVM", "Loading card for review: ${review.cardId}")
                val card = cardDao.getCardById(review.cardId)
                
                if (card == null) {
                    android.util.Log.e("FlashcardVM", "Card NOT found for ID: ${review.cardId}. Skipping...")
                    currentIndex++
                    showCard(currentIndex)
                    return@launch
                }
                
                android.util.Log.d("FlashcardVM", "Card found: ${card.word}")
                
                _uiState.update { 
                    it.copy(
                        currentCard = card, 
                        currentReview = review, 
                        isLoading = false,
                        currentIndex = index
                    ) 
                }
            }
        } else {
            android.util.Log.d("FlashcardVM", "All cards finished for this session.")
            _uiState.update { it.copy(currentCard = null, isFinished = true, isLoading = false) }
        }
    }


    fun playAudio(url: String?) {
        if (url.isNullOrBlank()) return
        viewModelScope.launch {
            try {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(url)
                    prepareAsync()
                    setOnPreparedListener { start() }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onRateCard(rating: Int) {
        val currentState = _uiState.value
        val card = currentState.currentCard ?: return
        val review = currentState.currentReview ?: return

        viewModelScope.launch {
            // Rating is already 0-5 from the UI
            val qualityRating = rating.coerceIn(0, 5)

            // 2. Calculate SM-2
            val result = calculateSM2(
                qualityRating = qualityRating,
                currentRepetitions = review.repetitions,
                currentInterval = review.interval,
                currentEaseFactor = review.easeFactor
            )

            // 3. Update Review
            val updatedReview = review.copy(
                repetitions = result.repetitions,
                interval = result.interval,
                easeFactor = result.easeFactor,
                lastDate = System.currentTimeMillis(),
                nextDate = result.nextDate
            )
            reviewDao.insertOrUpdateReview(updatedReview)

            // 4. Log Learning session
            learningLogDao.insertLog(
                LearningLogEntity(
                    userId = review.userId,
                    cardId = card.id,
                    qualityRating = qualityRating,
                    responseTime = 0, // Simplified for now
                    timestamp = System.currentTimeMillis()
                )
            )

            // 5. Update User Stats (Simplified Streak update)
            val stats = userStatsDao.getUserStats(review.userId).firstOrNull() 
                ?: UserStatsEntity(review.userId, 0, 0, 0, 10)
            
            userStatsDao.insertOrUpdateStats(stats.copy(
                currentStreak = if (qualityRating >= 3) stats.currentStreak + 1 else 0,
                totalXp = stats.totalXp + (qualityRating * 10)
            ))

            // 6. Move to next card
            currentIndex++
            showCard(currentIndex)
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
