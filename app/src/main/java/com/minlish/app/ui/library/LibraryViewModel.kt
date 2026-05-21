package com.minlish.app.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.domain.model.Topic
import com.minlish.app.domain.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: VocabularyRepository
) : ViewModel() {

    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    init {
        loadTopics()
    }

    private fun loadTopics() {
        viewModelScope.launch {
            repository.getAllTopics().collect { topicsList ->
                _topics.value = topicsList
            }
        }
    }
}
