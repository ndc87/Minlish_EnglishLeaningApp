package com.minlish.app.domain.usecase

import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.dao.ReviewDao
import javax.inject.Inject

data class AppAnalytics(
    val accuracy: Double,
    val retentionRate: Double,
    val levelEstimation: String,
    val learnedCount: Int,
    val dueCount: Int,
    val todayCount: Int,
    val masteredCount: Int
)

class GetAnalyticsUseCase @Inject constructor(
    private val learningLogDao: LearningLogDao,
    private val reviewDao: ReviewDao
) {
    private val MASTERED_INTERVAL_MINUTES = 30240 // 21 days

    suspend operator fun invoke(): AppAnalytics = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Default) {
        val totalReviews = learningLogDao.getTotalReviewCount()
        val correctReviews = learningLogDao.getCorrectReviewCount()
        
        // Accuracy: Percentage of correct reviews (quality >= 3) out of all reviews
        val accuracy = if (totalReviews > 0) {
            (correctReviews.toDouble() / totalReviews.toDouble()) * 100
        } else 0.0

        // Retention Rate: Percentage of cards that are NOT "Again" or newly added
        // A card is "retained" if its next review date is in the future
        val totalCards = reviewDao.getTotalCardCount()
        val currentTime = System.currentTimeMillis()
        val retainedCards = reviewDao.getRetainedCount(currentTime)
        
        val retentionRate = if (totalCards > 0) {
            (retainedCards.toDouble() / totalCards.toDouble()) * 100
        } else 0.0

        val learnedCount = reviewDao.getLearningCardCount(currentTime, MASTERED_INTERVAL_MINUTES)
        val dueCount = reviewDao.getDueCardCount(currentTime)
        val masteredCount = reviewDao.getMasteredCardCount(MASTERED_INTERVAL_MINUTES)

        // Calculate words studied today
        val startOfToday = java.util.Calendar.getInstance().apply {
            set(java.util.Calendar.HOUR_OF_DAY, 0)
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }.timeInMillis
        val todayCount = learningLogDao.getReviewCountSince(startOfToday)

        // Simple Level Estimation based on correct reviews (Total XP also reflects this)
        val levelEstimation = when {
            correctReviews > 500 -> "Advanced (C1)"
            correctReviews > 300 -> "Intermediate (B2)"
            correctReviews > 150 -> "Lower Intermediate (B1)"
            correctReviews > 50 -> "Elementary (A2)"
            else -> "Beginner (A1)"
        }

        AppAnalytics(
            accuracy = accuracy.coerceIn(0.0, 100.0),
            retentionRate = retentionRate.coerceIn(0.0, 100.0),
            levelEstimation = levelEstimation,
            learnedCount = learnedCount,
            dueCount = dueCount,
            todayCount = todayCount,
            masteredCount = masteredCount
        )
    }
}
