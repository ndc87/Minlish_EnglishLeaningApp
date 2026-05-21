package com.minlish.app.domain.usecase

import com.minlish.app.domain.model.SrsResult
import javax.inject.Inject

/**
 * Spaced Repetition Algorithm implementation.
 * Calculates the next review date and metrics based on learner performance.
 */
class CalculateSM2IntervalUseCase @Inject constructor() {

    /**
     * @param qualityRating 1: Again, 2: Hard, 3: Good, 4: Easy
     * @param currentInterval in minutes
     */
    operator fun invoke(
        qualityRating: Int,
        currentRepetitions: Int,
        currentInterval: Int,
        currentEaseFactor: Double
    ): SrsResult {
        
        var nextRepetitions: Int
        var nextInterval: Int
        var nextEaseFactor: Double = currentEaseFactor

        when (qualityRating) {
            1 -> { // Again - 1 minute
                nextRepetitions = 0
                nextInterval = 1
                nextEaseFactor = (currentEaseFactor - 0.20).coerceAtLeast(1.3)
            }
            2 -> { // Hard - 1 day (1440 mins)
                nextRepetitions = currentRepetitions + 1
                nextInterval = if (currentRepetitions == 0) 1440 else (currentInterval * 1.2).toInt()
                nextEaseFactor = (currentEaseFactor - 0.15).coerceAtLeast(1.3)
            }
            3 -> { // Good - 3 days (4320 mins)
                nextRepetitions = currentRepetitions + 1
                nextInterval = when (nextRepetitions) {
                    1 -> 4320
                    else -> (currentInterval * nextEaseFactor).toInt()
                }
            }
            4 -> { // Easy - 7 days (10080 mins)
                nextRepetitions = currentRepetitions + 1
                nextInterval = when (nextRepetitions) {
                    1 -> 10080
                    else -> (currentInterval * nextEaseFactor * 1.3).toInt()
                }
                nextEaseFactor = (currentEaseFactor + 0.15).coerceAtMost(2.5)
            }
            else -> {
                nextRepetitions = currentRepetitions
                nextInterval = currentInterval
            }
        }

        // Safety check for nextInterval
        if (nextInterval <= 0) nextInterval = 1

        // Calculate NextDate based on minutes
        val nextDate = System.currentTimeMillis() + (nextInterval * 60 * 1000L)

        return SrsResult(
            repetitions = nextRepetitions,
            interval = nextInterval,
            easeFactor = nextEaseFactor,
            nextDate = nextDate
        )
    }
}
