package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_logs")
data class StudyLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dateTimestamp: Long,
    val wordsReviewed: Int,
    val correctAnswers: Int
)
