package com.minlish.app.ui.practice

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.entity.CardEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val cardDao: com.minlish.app.data.local.dao.CardDao,
    private val reviewDao: com.minlish.app.data.local.dao.ReviewDao
) : ViewModel() {

    private var learnedCards: List<CardEntity> = emptyList()
    private var allCards: List<CardEntity> = emptyList()

    var currentCard by mutableStateOf<CardEntity?>(null)
        private set

    var options by mutableStateOf<List<String>>(emptyList())
        private set

    var selectedOption by mutableStateOf<String?>(null)
    var isCorrect by mutableStateOf<Boolean?>(null)
    var isRevealed by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    var score by mutableStateOf(0)
    var totalQuestions by mutableStateOf(0)

    private var mediaPlayer: MediaPlayer? = null

    init {
        viewModelScope.launch {
            // Load all cards for distractors
            allCards = cardDao.getAllCardsList()
            
            reviewDao.getLearnedCards().collect { 
                learnedCards = it
                if (currentCard == null && learnedCards.isNotEmpty()) loadNextCard()
            }
        }
    }

    fun loadNextCard() {
        if (learnedCards.isEmpty()) {
            currentCard = null
            return
        }
        
        isLoading = true
        val card = learnedCards.shuffled().first()
        currentCard = card
        generateOptions(card)
        selectedOption = null
        isCorrect = null
        isRevealed = false
        isLoading = false
    }

    private fun generateOptions(correctCard: CardEntity) {
        // Use all cards for distractors to ensure we always have 4 options
        val distractors = allCards
            .filter { it.id != correctCard.id }
            .shuffled()
            .take(3)
            .map { it.meaning }
        
        options = (distractors + correctCard.meaning).shuffled()
    }

    fun selectOption(option: String) {
        if (isRevealed) return
        selectedOption = option
        val card = currentCard ?: return
        isCorrect = option == card.meaning
        isRevealed = true
        totalQuestions++
        if (isCorrect == true) score++
    }

    fun playAudio(url: String) {
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

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
