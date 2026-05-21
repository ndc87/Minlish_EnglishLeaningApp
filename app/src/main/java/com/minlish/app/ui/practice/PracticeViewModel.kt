package com.minlish.app.ui.practice

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.dao.CardDao
import com.minlish.app.data.local.entity.CardEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(
    private val cardDao: CardDao
) : ViewModel() {

    var currentCard by mutableStateOf<CardEntity?>(null)
        private set

    var userInput by mutableStateOf("")
    var isCorrect by mutableStateOf<Boolean?>(null)
    var isRevealed by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    private var mediaPlayer: MediaPlayer? = null

    init {
        loadNextCard()
    }

    fun loadNextCard() {
        viewModelScope.launch {
            isLoading = true
            currentCard = cardDao.getRandomCard()
            userInput = ""
            isCorrect = null
            isRevealed = false
            isLoading = false
            currentCard?.audioUrl?.let { playAudio(it) }
        }
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

    fun checkAnswer() {
        val card = currentCard ?: return
        isCorrect = userInput.trim().equals(card.word, ignoreCase = true)
        isRevealed = true
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
