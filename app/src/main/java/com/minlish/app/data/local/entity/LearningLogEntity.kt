package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learning_logs")
data class LearningLogEntity(
    @PrimaryKey(autoGenerate = true)
    val logId: Long = 0,
    val userId: String,
    val cardId: Long,
    val qualityRating: Int,
    val responseTime: Long, // in milliseconds
    val timestamp: Long
)
