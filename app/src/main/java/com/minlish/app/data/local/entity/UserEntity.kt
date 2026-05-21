package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val username: String,
    val email: String,
    val profileLevel: String = "A1",
    val learningGoal: String = "Communication",
    val dailyGoalWords: Int = 10,
    val currentStreak: Int = 0,
    val token: String? = null
)
