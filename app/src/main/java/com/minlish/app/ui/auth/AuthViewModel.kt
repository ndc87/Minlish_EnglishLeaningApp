package com.minlish.app.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    val isLoggedIn: StateFlow<Boolean> = authRepository.isLoggedIn()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            // Simulating network delay
            kotlinx.coroutines.delay(1000)
            val result = authRepository.login(email, password)
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, success = true) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Invalid credentials") }
            }
        }
    }

    fun loginWithGoogle() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            // Simulating Google Sign-In process
            kotlinx.coroutines.delay(1500)
            val result = authRepository.loginWithGoogle()
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, success = true) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Google Sign-In failed") }
            }
        }
    }


    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            // Simulating network delay
            kotlinx.coroutines.delay(1000)
            val result = authRepository.register(name, email, password)
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, success = true) }
            } else {
                _uiState.update { it.copy(isLoading = false, error = "Registration failed") }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}
