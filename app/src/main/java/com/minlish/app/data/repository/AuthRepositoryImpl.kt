package com.minlish.app.data.repository

import com.minlish.app.data.local.storage.EncryptedAuthStorage
import com.minlish.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val encryptedAuthStorage: EncryptedAuthStorage
) : AuthRepository {

    private val _tokenFlow = MutableStateFlow(encryptedAuthStorage.getAuthToken())

    override suspend fun login(email: String, password: String): Result<String> {
        // Mock login logic for local testing
        return try {
            val mockToken = "mock_uid_${email.hashCode()}"
            encryptedAuthStorage.saveAuthToken(mockToken)
            _tokenFlow.value = mockToken
            Result.success(mockToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(name: String, email: String, password: String): Result<String> {
        // Mock register logic for local testing
        return try {
            val mockToken = "mock_uid_${email.hashCode()}"
            encryptedAuthStorage.saveAuthToken(mockToken)
            _tokenFlow.value = mockToken
            Result.success(mockToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun loginWithGoogle(): Result<String> {
        // Mock Google login logic
        return try {
            val mockGoogleToken = "mock_google_token_${System.currentTimeMillis()}"
            encryptedAuthStorage.saveAuthToken(mockGoogleToken)
            _tokenFlow.value = mockGoogleToken
            Result.success(mockGoogleToken)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun logout() {
        encryptedAuthStorage.clearAuthToken()
        _tokenFlow.value = null
    }

    override fun getAuthToken(): Flow<String?> = _tokenFlow

    override fun isLoggedIn(): Flow<Boolean> = _tokenFlow.map { it != null }
}
