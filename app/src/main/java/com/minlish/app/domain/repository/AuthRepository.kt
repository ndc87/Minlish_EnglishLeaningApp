package com.minlish.app.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<String>
    suspend fun register(name: String, email: String, password: String): Result<String>
    suspend fun loginWithGoogle(): Result<String>
    suspend fun logout()
    fun getAuthToken(): Flow<String?>
    fun isLoggedIn(): Flow<Boolean>
}
