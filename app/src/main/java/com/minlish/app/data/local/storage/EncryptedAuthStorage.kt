package com.minlish.app.data.local.storage

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class EncryptedAuthStorage @Inject constructor(
    @Named("encrypted_prefs") private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
    }

    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null)
    }

    fun clearAuthToken() {
        sharedPreferences.edit().remove(KEY_AUTH_TOKEN).apply()
    }
}
