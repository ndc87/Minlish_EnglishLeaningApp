package com.minlish.app.ui.practice

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minlish.app.R
import com.minlish.app.data.local.entity.CardEntity
import com.minlish.app.ui.theme.MinLishTheme

@Composable
fun PracticeScreen(
    viewModel: PracticeViewModel = hiltViewModel()
) {
    PracticeContent(
        card = viewModel.currentCard,
        userInput = viewModel.userInput,
        onUserInputChange = { viewModel.userInput = it },
        isRevealed = viewModel.isRevealed,
        isCorrect = viewModel.isCorrect,
        isLoading = viewModel.isLoading,
        onCheckAnswer = { viewModel.checkAnswer() },
        onNextCard = { viewModel.loadNextCard() },
        onPlayAudio = { viewModel.currentCard?.audioUrl?.let { viewModel.playAudio(it) } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeContent(
    card: CardEntity?,
    userInput: String,
    onUserInputChange: (String) -> Unit,
    isRevealed: Boolean,
    isCorrect: Boolean?,
    isLoading: Boolean,
    onCheckAnswer: () -> Unit,
    onNextCard: () -> Unit,
    onPlayAudio: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Practice", fontWeight = FontWeight.Bold) }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else if (card != null) {
                    // 5.A Audio Dictation Section
                    Card(
                        modifier = Modifier.size(120.dp),
                        shape = RoundedCornerShape(60.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                        onClick = onPlayAudio
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "Play Audio",
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "Listen and Type",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = userInput,
                        onValueChange = onUserInputChange,
                        label = { Text("Your answer") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !isRevealed,
                        isError = isCorrect == false,
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (!isRevealed) {
                        Button(
                            onClick = onCheckAnswer,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Check Answer", fontWeight = FontWeight.Bold)
                        }
                    } else {
                        val resultColor = if (isCorrect == true) Color(0xFF4CAF50) else Color(0xFFEF5350)
                        
                        Text(
                            text = if (isCorrect == true) "Excellent! ✨" else "Incorrect: ${card.word}",
                            color = resultColor,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 3. Component Stylings: Surface/Card for details
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = card.meaning, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "\"${card.example}\"", style = MaterialTheme.typography.bodyMedium, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = onNextCard,
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Refresh, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Try Next Word", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                } else {
                    Text(
                        text = "No words available for practice.\nAdd some words to your library first!",
                        textAlign = TextAlign.Center,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PracticePreview() {
    MinLishTheme {
        PracticeContent(
            card = CardEntity(word = "Negotiate", meaning = "Đàm phán", example = "They need to negotiate.", pos = "v", level = 2, topic = "Business", audioUrl = null, imageUrl = null),
            userInput = "Negociate",

            onUserInputChange = {},
            isRevealed = true,
            isCorrect = false,
            isLoading = false,
            onCheckAnswer = {},
            onNextCard = {},
            onPlayAudio = {}
        )
    }
}
