package com.minlish.app.ui.learning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minlish.app.domain.model.Word

@Composable
fun LearningScreen(
    topicId: String,
    onNavigateBack: () -> Unit,
    viewModel: LearningViewModel = hiltViewModel()
) {
    var isFlipped by remember { mutableStateOf(false) }
    val currentWord by viewModel.currentWord.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateBack, modifier = Modifier.align(Alignment.Start)) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (currentWord == null) {
            Text("No more words to review!", style = MaterialTheme.typography.titleLarge)
        } else {
            // Flashcard
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable { isFlipped = !isFlipped },
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    if (!isFlipped) {
                        Text(
                            text = currentWord?.word ?: "",
                            style = MaterialTheme.typography.headlineLarge,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Meaning: ${currentWord?.meaning}", style = MaterialTheme.typography.titleMedium)
                            Text(text = "Example: ${currentWord?.example}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // SRS Action Buttons
            if (isFlipped) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { viewModel.submitReview(1); isFlipped = false }) { Text("Again (1)") }
                    Button(onClick = { viewModel.submitReview(3); isFlipped = false }) { Text("Hard (3)") }
                    Button(onClick = { viewModel.submitReview(4); isFlipped = false }) { Text("Good (4)") }
                    Button(onClick = { viewModel.submitReview(5); isFlipped = false }) { Text("Easy (5)") }
                }
            } else {
                Text("Tap the card to reveal the answer")
            }
        }
    }
}
