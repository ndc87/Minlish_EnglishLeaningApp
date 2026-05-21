package com.minlish.app.domain.usecase

import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.dao.ReviewDao
import javax.inject.Inject

data class AppAnalytics(
    val accuracy: Double,
    val retentionRate: Double,
    val levelEstimation: String
)

class GetAnalyticsUseCase @Inject constructor(
    private val learningLogDao: LearningLogDao,
    private val reviewDao: ReviewDao
) {
    suspend operator fun invoke(): AppAnalytics {
        val totalReviews = learningLogDao.getTotalReviewCount()
        val correctReviews = learningLogDao.getCorrectReviewCount()
        
        // Accuracy: Percentage of correct reviews (quality >= 3) out of all reviews
        val accuracy = if (totalReviews > 0) {
            (correctReviews.toDouble() / totalReviews.toDouble()) * 100
        } else 0.0

        // Retention Rate: Percentage of cards that are NOT "Again" or newly added
        // A card is "retained" if its next review date is in the future
        val totalCards = reviewDao.getTotalCardCount()
        val retainedCards = reviewDao.getRetainedCount(System.currentTimeMillis())
        
        val retentionRate = if (totalCards > 0) {
            (retainedCards.toDouble() / totalCards.toDouble()) * 100
        } else 0.0

        // Simple Level Estimation based on correct reviews (Total XP also reflects this)
        val levelEstimation = when {
            correctReviews > 500 -> "Advanced (C1)"
            correctReviews > 300 -> "Intermediate (B2)"
            correctReviews > 150 -> "Lower Intermediate (B1)"
            correctReviews > 50 -> "Elementary (A2)"
            else -> "Beginner (A1)"
        }

        return AppAnalytics(
            accuracy = accuracy.coerceIn(0.0, 100.0),
            retentionRate = retentionRate.coerceIn(0.0, 100.0),
            levelEstimation = levelEstimation
        )
    }
}
