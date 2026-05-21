package com.minlish.app.ui.vocabulary

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    var isLoading by mutableStateOf(false)

    fun onAddClick(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (word.isBlank() || meaning.isBlank()) {
            onError("Word and Meaning cannot be empty")
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
                topic = if (topic.isBlank()) "General" else topic
            )
                .onSuccess { onSuccess() }
                .onFailure { onError(it.message ?: "Unknown error") }
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
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Word") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.word,
                onValueChange = { viewModel.word = it },
                label = { Text("Word") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.pos,
                onValueChange = { viewModel.pos = it },
                label = { Text("Part of Speech (e.g. noun, verb)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.meaning,
                onValueChange = { viewModel.meaning = it },
                label = { Text("Meaning") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.example,
                onValueChange = { viewModel.example = it },
                label = { Text("Example Sentence") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )
            OutlinedTextField(
                value = viewModel.pronunciation,
                onValueChange = { viewModel.pronunciation = it },
                label = { Text("Pronunciation (e.g. /əˈɡriːmənt/)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.descriptionEn,
                onValueChange = { viewModel.descriptionEn = it },
                label = { Text("English Description") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.topic,
                onValueChange = { viewModel.topic = it },
                label = { Text("Topic (e.g. Business, Travel)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    viewModel.onAddClick(
                        onSuccess = {
                            Toast.makeText(context, "Word added successfully", Toast.LENGTH_SHORT).show()
                            onBack()
                        },
                        onError = { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !viewModel.isLoading
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Add to Collection")
                }
            }
        }
    }
}
