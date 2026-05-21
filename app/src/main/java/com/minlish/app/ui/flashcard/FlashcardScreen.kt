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
                FlashcardContent(
                    card = uiState.currentCard!!,
                    onRate = { viewModel.onRateCard(it) },
                    onPlayAudio = { viewModel.playAudio(uiState.currentCard!!.audioUrl) }
                )
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
fun FlashcardContent(
    card: CardEntity,
    onRate: (Int) -> Unit,
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
        // 2. Core Layout: Box for stacking Front/Back
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                }
                .clickable { rotated = !rotated },
            contentAlignment = Alignment.Center
        ) {
            if (rotation <= 90f) {
                // 5.A Front: ONLY target word
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(16.dp), // 3. Larger for Flashcard
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = card.word,
                            fontSize = 32.sp, // 4. Typography
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                // 5.A Back: meaning, example, audio
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = card.word,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        card.pronunciation?.let {
                            Text(
                                text = it,
                                fontSize = 18.sp,
                                color = Color.Gray,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = card.meaning,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = card.example,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        IconButton(onClick = onPlayAudio) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = stringResource(R.string.audio_desc),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }


                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // 5.A Evaluation Footer: Colored buttons side-by-side
        AnimatedVisibility(visible = rotated || rotation > 90f) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // 2. Core Layout
            ) {
                SrsButton(stringResource(R.string.srs_again), Color(0xFFE57373)) { onRate(0) }
                SrsButton(stringResource(R.string.srs_hard), Color(0xFFFFB74D)) { onRate(2) }
                SrsButton(stringResource(R.string.srs_good), Color(0xFF81C784)) { onRate(4) }
                SrsButton(stringResource(R.string.srs_easy), Color(0xFF64B5F6)) { onRate(5) }
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
        FlashcardContent(
            card = CardEntity(word = "Contract", meaning = "Hợp đồng", example = "Sign the contract.", level = 1, pos = "n", audioUrl = null, imageUrl = null, topic = "General"),
            onRate = {},
            onPlayAudio = {}
        )
    }
}

