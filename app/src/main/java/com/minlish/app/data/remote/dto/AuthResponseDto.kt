package com.minlish.app.data.remote.dto

data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val userId: String,
    val username: String
)

data class TopicDto(
    val id: String,
    val name: String,
    val description: String,
    val tags: List<String>
)

data class WordDto(
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
