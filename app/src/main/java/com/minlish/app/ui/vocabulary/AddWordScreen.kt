package com.minlish.app.ui.vocabulary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.domain.usecase.AddNewWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWordViewModel @Inject constructor(
    private val addNewWordUseCase: AddNewWordUseCase
) : ViewModel() {
    var word by mutableStateOf("")
    var pos by mutableStateOf("")
    var meaning by mutableStateOf("")
    var example by mutableStateOf("")
    var pronunciation by mutableStateOf("")
    var descriptionEn by mutableStateOf("")
    var topic by mutableStateOf("")
    var collocation by mutableStateOf("")
    var relatedWords by mutableStateOf("")
    var note by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun onAddClick(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (word.isBlank() || meaning.isBlank()) {
            onError("Please fill in word and meaning")
            return
        }
        viewModelScope.launch {
            isLoading = true
            addNewWordUseCase(
                word = word,
                pos = pos,
                meaning = meaning,
                example = example,
                pronunciation = pronunciation,
                descriptionEn = descriptionEn,
                topic = topic.ifBlank { "General" },
                collocation = collocation,
                relatedWords = relatedWords,
                note = note
            ).onSuccess {
                onSuccess()
            }.onFailure {
                onError(it.message ?: "Unknown error")
            }
            isLoading = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordScreen(
    onBack: () -> Unit,
    viewModel: AddWordViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Add New Word", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.word,
                onValueChange = { viewModel.word = it },
                label = { Text("Word (English)*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = viewModel.pos,
                    onValueChange = { viewModel.pos = it },
                    label = { Text("Type (n, v, adj...)") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    value = viewModel.pronunciation,
                    onValueChange = { viewModel.pronunciation = it },
                    label = { Text("Pronunciation") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            OutlinedTextField(
                value = viewModel.meaning,
                onValueChange = { viewModel.meaning = it },
                label = { Text("Meaning (Vietnamese)*") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = viewModel.descriptionEn,
                onValueChange = { viewModel.descriptionEn = it },
                label = { Text("English Definition") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = viewModel.example,
                onValueChange = { viewModel.example = it },
                label = { Text("Example Sentence") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                minLines = 2
            )

            OutlinedTextField(
                value = viewModel.collocation,
                onValueChange = { viewModel.collocation = it },
                label = { Text("Collocations") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = viewModel.relatedWords,
                onValueChange = { viewModel.relatedWords = it },
                label = { Text("Related Words") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = viewModel.topic,
                onValueChange = { viewModel.topic = it },
                label = { Text("Topic") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = viewModel.note,
                onValueChange = { viewModel.note = it },
                label = { Text("Note / Example Meaning") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                minLines = 2
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.onAddClick(
                        onSuccess = onBack,
                        onError = { /* Show snackbar */ }
                    )
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                enabled = !viewModel.isLoading
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(24.dp))
                } else {
                    Text("Save Word", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
