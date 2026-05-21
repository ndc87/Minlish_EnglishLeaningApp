package com.minlish.app.domain.usecase

import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.dao.UserStatsDao
import com.minlish.app.data.local.entity.UserStatsEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CalculateStreakUseCase @Inject constructor(
    private val learningLogDao: LearningLogDao,
    private val userStatsDao: UserStatsDao
) {
    suspend operator fun invoke(userId: String) {
        val dates = learningLogDao.getAllStudyDates() // Format: YYYY-MM-DD, sorted DESC
        
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = sdf.format(Date())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday = sdf.format(calendar.time)

        if (dates.isEmpty()) {
            updateStats(userId, 0)
            return
        }

        // If no study today AND no study yesterday, streak is 0
        if (!dates.contains(today) && !dates.contains(yesterday)) {
            updateStats(userId, 0)
            return
        }

        // Calculate streak
        var streak = 0
        val checkCalendar = Calendar.getInstance()
        
        // Start from today if exists, else start from yesterday
        val startDateStr = if (dates.contains(today)) today else yesterday
        val startDate = sdf.parse(startDateStr) ?: return
        checkCalendar.time = startDate
        
        var currentCheckDate = sdf.format(checkCalendar.time)
        
        while (dates.contains(currentCheckDate)) {
            streak++
            checkCalendar.add(Calendar.DAY_OF_YEAR, -1)
            currentCheckDate = sdf.format(checkCalendar.time)
        }

        updateStats(userId, streak)
    }

    private suspend fun updateStats(userId: String, streak: Int) {
        val stats = userStatsDao.getUserStatsOnce(userId) ?: UserStatsEntity(userId, 0, 0, 0, 10)
        if (stats.currentStreak != streak) {
            userStatsDao.insertOrUpdateStats(stats.copy(currentStreak = streak))
        }
    }
}
