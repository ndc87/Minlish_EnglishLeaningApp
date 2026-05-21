package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String,
    val pos: String, // Part of Speech (noun, verb, etc.)
    val meaning: String,
    val example: String,
    val pronunciation: String? = null, // Added for audit requirement 1
    val descriptionEn: String? = null, // Added for audit requirement 1
    val collocation: String? = null,   // Added for audit requirement 1
    val relatedWords: String? = null, // Added for audit requirement 1
    val note: String? = null,          // Added for audit requirement 1
    val audioUrl: String?,
    val imageUrl: String?,
    val level: Int,
    val topic: String = "General",
    val topicUrl: String? = null
)


