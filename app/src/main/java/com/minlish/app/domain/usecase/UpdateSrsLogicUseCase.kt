package com.minlish.app.domain.usecase

import com.minlish.app.domain.model.SrsLog
import com.minlish.app.domain.repository.VocabularyRepository
import javax.inject.Inject

class UpdateSrsLogicUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    suspend operator fun invoke(wordId: String, quality: Int) {
        // SM-2 Algorithm implementation
        val currentLog = repository.getSrsLog(wordId)

        var interval = currentLog?.interval ?: 0
        var easeFactor = currentLog?.easeFactor ?: 2.5
        var repetitions = currentLog?.repetitionCount ?: 0

        if (quality >= 3) {
            when (repetitions) {
                0 -> interval = 1
                1 -> interval = 6
                else -> interval = (interval * easeFactor).toInt()
            }
            repetitions++
            easeFactor += (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02))
        } else {
            repetitions = 0
            interval = 1
        }

        if (easeFactor < 1.3) easeFactor = 1.3

        val nextReviewDate = System.currentTimeMillis() + (interval * 24L * 60L * 60L * 1000L)

        val updatedLog = SrsLog(
            wordId = wordId,
            interval = interval,
            easeFactor = easeFactor,
            repetitionCount = repetitions,
            nextReviewDate = nextReviewDate
        )

        repository.updateSrsLog(updatedLog)
    }
}
