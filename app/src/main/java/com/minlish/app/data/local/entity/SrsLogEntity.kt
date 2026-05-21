package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "srs_logs",
    foreignKeys = [
        ForeignKey(
            entity = WordEntity::class,
            parentColumns = ["id"],
            childColumns = ["wordId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("wordId")]
)
data class SrsLogEntity(
    @PrimaryKey
    val wordId: String,
    val interval: Int,
    val easeFactor: Double,
    val repetitionCount: Int,
    val nextReviewDate: Long
)
