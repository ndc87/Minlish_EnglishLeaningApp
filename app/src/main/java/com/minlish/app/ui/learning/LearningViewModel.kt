package com.minlish.app.ui.learning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.domain.model.Word
import com.minlish.app.domain.repository.VocabularyRepository
import com.minlish.app.domain.usecase.GetDueWordsUseCase
import com.minlish.app.domain.usecase.UpdateSrsLogicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearningViewModel @Inject constructor(
    private val getDueWordsUseCase: GetDueWordsUseCase,
    private val updateSrsLogicUseCase: UpdateSrsLogicUseCase,
    private val repository: VocabularyRepository
) : ViewModel() {

    private val wordsQueue = mutableListOf<Word>()

    private val _currentWord = MutableStateFlow<Word?>(null)
    val currentWord: StateFlow<Word?> = _currentWord

    init {
        loadDueWords()
    }

    private fun loadDueWords() {
        viewModelScope.launch {
            getDueWordsUseCase().collect { words ->
                wordsQueue.clear()
                wordsQueue.addAll(words)
                showNextWord()
            }
        }
    }

    private fun showNextWord() {
        if (wordsQueue.isNotEmpty()) {
            _currentWord.value = wordsQueue.removeAt(0)
        } else {
            _currentWord.value = null
        }
    }

    fun submitReview(quality: Int) {
        val word = _currentWord.value ?: return
        viewModelScope.launch {
            updateSrsLogicUseCase(word.id, quality)
            showNextWord()
        }
    }
}
