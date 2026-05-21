package com.minlish.app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minlish.app.data.local.entity.UserEntity
import com.minlish.app.ui.theme.MinLishTheme

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()
    
    ProfileContent(
        user = user,
        onUpdateProfile = { n, g, l -> viewModel.updateProfile(n, g, l) },
        onLogout = onLogout
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    user: UserEntity?,
    onUpdateProfile: (String, String, String) -> Unit,
    onLogout: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var learningGoal by remember { mutableStateOf("Communication") }
    var level by remember { mutableStateOf("A1") }
    val scrollState = rememberScrollState()

    LaunchedEffect(user) {
        user?.let {
            name = it.username
            learningGoal = it.learningGoal
            level = it.profileLevel
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Logout", tint = MaterialTheme.colorScheme.error)
                    }
                }

            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar Placeholder
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(60.dp), tint = MaterialTheme.colorScheme.onPrimaryContainer)
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Display Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 5.B Component Stylings
            Text(text = "Target Goal", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("IELTS", "TOEIC", "Daily").forEach { goal ->
                    FilterChip(
                        selected = learningGoal == goal,
                        onClick = { learningGoal = goal },
                        label = { Text(goal) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "English Level", style = MaterialTheme.typography.titleMedium, modifier = Modifier.align(Alignment.Start), fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
            val levels = listOf("A1", "A2", "B1", "B2", "C1", "C2")
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                levels.chunked(3).forEach { row ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        row.forEach { lvl ->
                            FilterChip(
                                selected = level == lvl,
                                onClick = { level = lvl },
                                label = { Text(lvl) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { onUpdateProfile(name, learningGoal, level) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save Changes", fontWeight = FontWeight.Bold)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MinLishTheme {
        ProfileContent(
            user = UserEntity("1", "John Doe", "john@example.com", "B1", "TOEIC"),
            onUpdateProfile = { _, _, _ -> },
            onLogout = {}
        )
    }
}
