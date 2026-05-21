package com.minlish.app.domain.usecase

import com.minlish.app.data.local.dao.StudyLogDao
import com.minlish.app.data.local.dao.UserDao
import kotlinx.coroutines.flow.firstOrNull
import java.util.Calendar
import javax.inject.Inject

class CalculateStreakUseCase @Inject constructor(
    private val studyLogDao: StudyLogDao,
    private val userDao: UserDao
) {
    suspend operator fun invoke() {
        // Get the logs to calculate active days
        // Here we simplify by checking if the user met their goal yesterday
        val user = userDao.getUser().firstOrNull() ?: return
        
        val calendar = Calendar.getInstance()
        
        // Define today bounds
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startOfToday = calendar.timeInMillis
        val endOfToday = startOfToday + 86400000L
        
        // Yesterday bounds
        val startOfYesterday = startOfToday - 86400000L
        val endOfYesterday = startOfToday
        
        val yesterdayLogs = studyLogDao.getLogsForDay(startOfYesterday, endOfYesterday)
        val todayLogs = studyLogDao.getLogsForDay(startOfToday, endOfToday)
        
        val reviewedYesterday = yesterdayLogs.sumOf { it.wordsReviewed }
        val reviewedToday = todayLogs.sumOf { it.wordsReviewed }
        
        // Basic streak logic
        var currentStreak = user.currentStreak
        
        if (reviewedYesterday == 0 && reviewedToday == 0) {
            currentStreak = 0 // Streak broken
        } else if (reviewedToday >= user.dailyGoalWords && reviewedYesterday >= user.dailyGoalWords) {
            // Continuation logic can be expanded
        }
        
        if(currentStreak != user.currentStreak) {
            userDao.updateStreak(currentStreak)
        }
    }
}
