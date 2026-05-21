package com.minlish.app.ui.flashcard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.minlish.app.R
import com.minlish.app.data.local.entity.CardEntity
import com.minlish.app.ui.theme.MinLishTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardScreen(
    onBack: () -> Unit,
    viewModel: FlashcardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.flashcard_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            if (uiState.isLoading) {
                Box(contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            } else if (uiState.currentCard != null) {
                when (uiState.currentStage) {
                    LearnStage.STUDY -> {
                        FlashcardStudyContent(
                            card = uiState.currentCard!!,
                            onNextStage = { viewModel.nextStage() },
                            onPlayAudio = { viewModel.playAudio(uiState.currentCard!!.audioUrl) }
                        )
                    }
                    LearnStage.RECOGNIZE -> {
                        FlashcardQuizzContent(
                            card = uiState.currentCard!!,
                            options = uiState.options,
                            selectedOption = uiState.selectedOption,
                            isCorrect = uiState.isCorrect,
                            onOptionClick = { viewModel.handleQuizzAnswer(it) },
                            onNextStage = { viewModel.nextStage() }
                        )
                    }
                    LearnStage.PRODUCE -> {
                        FlashcardWritingContent(
                            card = uiState.currentCard!!,
                            isCorrect = uiState.isCorrect,
                            onCheckAnswer = { viewModel.handleWritingAnswer(it) },
                            onRate = { viewModel.onRateCard(it) }
                        )
                    }
                }
            } else if (uiState.isFinished) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "You've finished all cards for now!",
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onBack) { Text("Back to Dashboard") }
                    }
                }
            } else {
                // Empty state or "No cards to learn"
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = "No cards available to learn right now.", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun FlashcardStudyContent(
    card: CardEntity,
    onNextStage: () -> Unit,
    onPlayAudio: () -> Unit
) {
    var rotated by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "flipAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bước 1: Làm quen từ mới", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                }
                .clickable { rotated = !rotated },
            contentAlignment = Alignment.Center
        ) {
            if (rotation <= 90f) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = card.word, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f },
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        card.imageUrl?.let { url ->
                            if (url.isNotBlank()) {
                                AsyncImage(
                                    model = url,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                        Text(text = card.word, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Text(text = card.pronunciation ?: "", color = Color.Gray)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = card.meaning, fontSize = 28.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = card.example, textAlign = TextAlign.Center, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onNextStage,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Tôi đã hiểu, sang bước tiếp theo", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FlashcardQuizzContent(
    card: CardEntity,
    options: List<String>,
    selectedOption: String?,
    isCorrect: Boolean?,
    onOptionClick: (String) -> Unit,
    onNextStage: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bước 2: Kiểm tra nhận diện", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = card.word, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        options.forEach { option ->
            val isThisSelected = option == selectedOption
            val color = when {
                isThisSelected && isCorrect == true -> Color(0xFFC8E6C9)
                isThisSelected && isCorrect == false -> Color(0xFFFFCDD2)
                isCorrect == false && option == card.meaning -> Color(0xFFC8E6C9)
                else -> MaterialTheme.colorScheme.surface
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable(enabled = selectedOption == null) { onOptionClick(option) },
                shape = RoundedCornerShape(12.dp),
                color = color,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
            ) {
                Text(text = option, modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Medium)
            }
        }

        if (selectedOption != null) {
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onNextStage, modifier = Modifier.fillMaxWidth()) {
                Text("Tiếp tục")
            }
        }
    }
}

@Composable
fun FlashcardWritingContent(
    card: CardEntity,
    isCorrect: Boolean?,
    onCheckAnswer: (String) -> Unit,
    onRate: (Int) -> Unit
) {
    var text by remember(card.id) { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bước 3: Viết lại từ vựng", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = card.meaning, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(text = "(${card.pos})", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { if (isCorrect == null) text = it },
            label = { Text("Nhập từ tiếng Anh") },
            modifier = Modifier.fillMaxWidth(),
            isError = isCorrect == false,
            enabled = isCorrect == null
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isCorrect == null) {
            Button(onClick = { onCheckAnswer(text) }, modifier = Modifier.fillMaxWidth()) {
                Text("Kiểm tra")
            }
        } else {
            Text(
                text = if (isCorrect) "Chính xác! 🎉" else "Chưa đúng rồi. Đáp án: ${card.word}",
                color = if (isCorrect) Color(0xFF4CAF50) else Color.Red,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            Text("Bạn thấy từ này thế nào?", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                SrsButton("Lại", Color(0xFFE57373)) { onRate(1) }
                SrsButton("Khó", Color(0xFFFFB74D)) { onRate(2) }
                SrsButton("Tốt", Color(0xFF81C784)) { onRate(3) }
                SrsButton("Dễ", Color(0xFF64B5F6)) { onRate(4) }
            }
        }
    }
}

@Composable
fun SrsButton(label: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.width(80.dp)
    ) {
        Text(text = label, fontSize = 12.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun FlashcardPreview() {
    MinLishTheme {
        FlashcardStudyContent(
            card = CardEntity(word = "Contract", meaning = "Hợp đồng", example = "Sign the contract.", level = 1, pos = "n", audioUrl = null, imageUrl = null, topic = "General"),
            onNextStage = {},
            onPlayAudio = {}
        )
    }
}

