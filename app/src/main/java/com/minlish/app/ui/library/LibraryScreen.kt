package com.minlish.app.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LibraryScreen(
    onNavigateBack: () -> Unit,
    onNavigateToLearning: (String) -> Unit,
    viewModel: LibraryViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = onNavigateBack) {
            Text("Back")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text("Topic Library")
        
        // Placeholder for Topic List
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onNavigateToLearning("topic_1") }) {
            Text("Study Topic 1")
        }
    }
}
