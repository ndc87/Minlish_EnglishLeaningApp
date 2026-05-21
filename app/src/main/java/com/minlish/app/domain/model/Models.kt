package com.minlish.app.domain.model

data class Topic(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>
)

data class Word(
    val id: String,
    val topicId: String,
    val word: String,
    val pronunciation: String,
    val meaning: String,
    val descriptionEn: String,
    val example: String,
    val collocation: String,
    val relatedWords: List<String>,
    val note: String
)

data class SrsLog(
    val wordId: String,
    val interval: Int,
    val easeFactor: Double,
    val repetitionCount: Int,
    val nextReviewDate: Long
)

data class User(
    val id: String,
    val username: String,
    val profileLevel: Int,
    val dailyGoalWords: Int,
    val currentStreak: Int
)
