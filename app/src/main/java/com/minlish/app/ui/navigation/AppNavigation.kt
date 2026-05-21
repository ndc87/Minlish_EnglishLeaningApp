package com.minlish.app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.minlish.app.ui.auth.AuthViewModel
import com.minlish.app.ui.auth.LoginScreen
import com.minlish.app.ui.auth.RegisterScreen
import com.minlish.app.ui.dashboard.DashboardScreen
import com.minlish.app.ui.flashcard.FlashcardScreen
import com.minlish.app.ui.profile.ProfileScreen
import com.minlish.app.ui.vocabulary.VocabScreen
import com.minlish.app.ui.vocabulary.AddWordScreen
import com.minlish.app.ui.splash.SplashScreen
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    // val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val isLoggedIn = true // Temporarily force logged in for UI testing


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    val showBottomBar = bottomNavItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { screen.icon?.let { Icon(it, contentDescription = screen.title) } },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route, // Skip Splash/Login for testing
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Splash.route) {
                SplashScreen(onInitializationComplete = {
                    val nextRoute = if (isLoggedIn) Screen.Dashboard.route else Screen.Login.route
                    navController.navigate(nextRoute) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                })
            }
            composable(Screen.Login.route) {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                    onLoginSuccess = { 
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Register.route) {
                RegisterScreen(
                    onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                    onRegisterSuccess = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Flashcard.route) { 
                FlashcardScreen(onBack = { navController.popBackStack() }) 
            }
            composable(Screen.Vocabulary.route) { 
                VocabScreen(onAddWord = { navController.navigate(Screen.AddWord.route) }) 
            }


            composable(Screen.AddWord.route) {
                AddWordScreen(onBack = { navController.popBackStack() })
            }
            composable(Screen.Profile.route) { 
                ProfileScreen(
                    onLogout = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                ) 
            }
        }
    }
}
