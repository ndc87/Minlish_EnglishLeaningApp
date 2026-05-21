package com.minlish.app.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.DatabaseSeeder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val databaseSeeder: DatabaseSeeder
) : ViewModel() {

    var isInitializationComplete by mutableStateOf(false)
        private set
    
    var statusText by mutableStateOf("Initializing...")
        private set

    init {
        initializeApplication()
    }

    private fun initializeApplication() {
        viewModelScope.launch {
            statusText = "Setting up your library..."
            // Give UI a moment to show the message
            delay(500) 
            
            databaseSeeder.seedIfNecessary()
            
            statusText = "Ready!"
            delay(500)
            isInitializationComplete = true
        }
    }
}
