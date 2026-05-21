package com.minlish.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey
    val cardId: Long,
    val userId: String,
    val repetitions: Int,
    val interval: Int,
    val easeFactor: Double,
    val lastDate: Long,
    val nextDate: Long
)
