package com.minlish.app.ui.vocabulary

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.dao.CardDao
import com.minlish.app.data.local.entity.CardEntity
import com.minlish.app.domain.usecase.ExportImportUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabularyViewModel @Inject constructor(
    private val cardDao: CardDao,
    private val exportImportUseCase: ExportImportUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _selectedFilter = MutableStateFlow(SrsFilter.ALL)
    val selectedFilter: StateFlow<SrsFilter> = _selectedFilter

    val filteredCards: StateFlow<List<CardEntity>> = combine(
        cardDao.getAllCards(),
        _searchQuery,
        _selectedFilter
    ) { cards, query, filter ->
        cards.filter { card ->
            val matchesQuery = card.word.contains(query, ignoreCase = true) || 
                             card.meaning.contains(query, ignoreCase = true)
            
            val matchesFilter = when (filter) {
                SrsFilter.ALL -> true
                SrsFilter.NEW -> card.level == 0
                SrsFilter.DUE -> card.level in 1..3 // Simplified logic for mock
                SrsFilter.MASTERED -> card.level > 3
            }
            
            matchesQuery && matchesFilter
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onFilterChange(filter: SrsFilter) {
        _selectedFilter.value = filter
    }

    fun importCsv(uri: Uri) {
        viewModelScope.launch {
            exportImportUseCase.importFromCsv(uri)
                .onSuccess { _uiEvent.emit(UiEvent.ShowToast("Import successful")) }
                .onFailure { _uiEvent.emit(UiEvent.ShowToast("Import failed: ${it.message}")) }
        }
    }

    fun exportCsv(uri: Uri) {
        viewModelScope.launch {
            exportImportUseCase.exportToCsv(uri)
                .onSuccess { _uiEvent.emit(UiEvent.ShowToast("Export successful")) }
                .onFailure { _uiEvent.emit(UiEvent.ShowToast("Export failed: ${it.message}")) }
        }
    }

    enum class SrsFilter(val label: String, val color: Color) {
        ALL("All", Color(0xFFA020F0)), // Purple
        NEW("New", Color(0xFF2196F3)), // Blue
        DUE("Due", Color(0xFFFF9800)), // Orange
        MASTERED("Mastered", Color(0xFF4CAF50)) // Green
    }

    sealed class UiEvent {
        data class ShowToast(val message: String) : UiEvent()
    }
}

