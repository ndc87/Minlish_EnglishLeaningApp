package com.minlish.app.domain.usecase

import com.minlish.app.domain.model.SrsResult
import java.util.Calendar
import javax.inject.Inject

/**
 * Spaced Repetition (SM-2) Algorithm implementation.
 * Calculates the next review date and metrics based on learner performance.
 */
class CalculateSM2IntervalUseCase @Inject constructor() {

    operator fun invoke(
        qualityRating: Int, // 0 to 5
        currentRepetitions: Int,
        currentInterval: Int,
        currentEaseFactor: Double
    ): SrsResult {
        
        var nextRepetitions: Int
        var nextInterval: Int
        var nextEaseFactor: Double

        // Logic for Ease Factor update
        // EF = EF + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02))
        nextEaseFactor = currentEaseFactor + (0.1 - (5 - qualityRating) * (0.08 + (5 - qualityRating) * 0.02))
        if (nextEaseFactor < 1.3) nextEaseFactor = 1.3

        if (qualityRating >= 3) {
            // Success: Calculate next interval
            nextRepetitions = currentRepetitions + 1
            nextInterval = when (nextRepetitions) {
                1 -> 1
                2 -> 6
                else -> (currentInterval * nextEaseFactor).toInt()
            }
        } else {
            // Failure: Reset repetitions but keep Ease Factor
            nextRepetitions = 0
            nextInterval = 1
        }

        // Calculate NextDate
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, nextInterval)
        val nextDate = calendar.timeInMillis

        return SrsResult(
            repetitions = nextRepetitions,
            interval = nextInterval,
            easeFactor = nextEaseFactor,
            nextDate = nextDate
        )
    }
}
