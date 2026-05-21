package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey
    val userId: String,
    val currentStreak: Int,
    val totalXp: Int,
    val masteredCount: Int,
    val dailyGoal: Int
)
