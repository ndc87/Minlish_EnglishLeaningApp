package com.minlish.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String = "", val icon: ImageVector? = null) {
    object Splash : Screen("splash")
    object Dashboard : Screen("dashboard", "Dashboard", Icons.Default.Home)
    object Flashcard : Screen("flashcard", "Learn", Icons.Default.DateRange)
    object Practice : Screen("practice", "Practice", Icons.Default.Edit)
    object LearnSession : Screen("learn_session/{topicId}")
    object TopicSelection : Screen("topic_selection")
    object Vocabulary : Screen("vocabulary", "Vocab", Icons.Default.List)
    object AddWord : Screen("add_word")
    object Login : Screen("login")
    object Register : Screen("register")
    object Profile : Screen("profile", "Profile")
}

val bottomNavItems = listOf(
    Screen.Dashboard,
    Screen.Flashcard,
    Screen.Practice,
    Screen.Vocabulary,
    Screen.Profile
)
