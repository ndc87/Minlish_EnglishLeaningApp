package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    foreignKeys = [
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = ["id"],
            childColumns = ["topicId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("topicId")]
)
data class WordEntity(
    @PrimaryKey
    val id: String,
    val topicId: String,
    val word: String,
    val pronunciation: String,
    val meaning: String,
    val descriptionEn: String,
    val example: String,
    val collocation: String,
    val relatedWords: String,
    val note: String
)
