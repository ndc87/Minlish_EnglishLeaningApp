package com.minlish.app.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.dao.UserDao
import com.minlish.app.data.local.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user = _user.asStateFlow()

    private val currentUserId = "current_user_id" // Mock current user ID

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _user.value = userDao.getUser().firstOrNull()
        }
    }

    fun updateProfile(username: String, learningGoal: String, level: String) {
        viewModelScope.launch {
            val currentUser = _user.value ?: UserEntity(id = currentUserId, username = username, email = "", profileLevel = level, learningGoal = learningGoal)
            val updatedUser = currentUser.copy(username = username, learningGoal = learningGoal, profileLevel = level)
            userDao.insertUser(updatedUser)
            _user.value = updatedUser
        }
    }
}
