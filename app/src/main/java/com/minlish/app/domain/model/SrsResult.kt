package com.minlish.app.domain.model

data class SrsResult(
    val repetitions: Int,
    val interval: Int,
    val easeFactor: Double,
    val nextDate: Long
)
