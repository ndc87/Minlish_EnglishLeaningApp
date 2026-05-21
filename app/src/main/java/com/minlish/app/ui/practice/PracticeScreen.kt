package com.minlish.app.ui.practice

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minlish.app.data.local.entity.CardEntity

@Composable
fun PracticeScreen(
    viewModel: PracticeViewModel = hiltViewModel()
) {
    PracticeContent(
        card = viewModel.currentCard,
        options = viewModel.options,
        score = viewModel.score,
        total = viewModel.totalQuestions,
        isRevealed = viewModel.isRevealed,
        selectedOption = viewModel.selectedOption,
        isLoading = viewModel.isLoading,
        onOptionClick = { viewModel.selectOption(it) },
        onNextClick = { viewModel.loadNextCard() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeContent(
    card: CardEntity?,
    options: List<String>,
    score: Int,
    total: Int,
    isRevealed: Boolean,
    selectedOption: String?,
    isLoading: Boolean,
    onOptionClick: (String) -> Unit,
    onNextClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Practice Quizz", fontWeight = FontWeight.ExtraBold) },
                actions = {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Text(
                            text = "⭐ $score/$total",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (card != null) {
                // Word Display
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Chọn nghĩa đúng cho từ:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = card.word,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Options
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    options.forEach { option ->
                        val isCorrectOption = isRevealed && option == card.meaning
                        val isSelectedWrong = isRevealed && option == selectedOption && option != card.meaning
                        
                        val containerColor = when {
                            isCorrectOption -> Color(0xFFE8F5E9) // Very Light Green
                            isSelectedWrong -> Color(0xFFFFEBEE) // Very Light Red
                            option == selectedOption && !isRevealed -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.surface
                        }

                        val contentColor = when {
                            isCorrectOption -> Color(0xFF2E7D32) // Dark Green
                            isSelectedWrong -> Color(0xFFC62828) // Dark Red
                            else -> MaterialTheme.colorScheme.onSurface
                        }

                        val borderColor = when {
                            isCorrectOption -> Color(0xFF4CAF50)
                            isSelectedWrong -> Color(0xFFF44336)
                            option == selectedOption -> MaterialTheme.colorScheme.primary
                            else -> Color.LightGray.copy(alpha = 0.5f)
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(enabled = !isRevealed) { onOptionClick(option) },
                            shape = RoundedCornerShape(16.dp),
                            color = containerColor,
                            border = BorderStroke(2.dp, borderColor),
                            shadowElevation = 2.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = option,
                                    modifier = Modifier.weight(1f),
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.SemiBold,
                                    color = contentColor
                                )
                                if (isCorrectOption) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF4CAF50))
                                } else if (isSelectedWrong) {
                                    Icon(Icons.Default.Close, contentDescription = null, tint = Color(0xFFF44336))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Next Button
                if (isRevealed) {
                    Button(
                        onClick = onNextClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Câu tiếp theo", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Chưa có từ vựng nào để luyện tập!", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Bạn hãy vào tab Learn để học từ mới trước nhé.", color = Color.Gray, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
