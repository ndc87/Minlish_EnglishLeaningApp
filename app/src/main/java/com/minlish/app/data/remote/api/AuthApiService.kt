package com.minlish.app.data.remote.api

import com.minlish.app.data.remote.dto.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/auth/login")
    suspend fun login(@Body request: Map<String, String>): AuthResponseDto

    @POST("api/auth/refresh")
    suspend fun refreshToken(@Body request: Map<String, String>): AuthResponseDto
}
