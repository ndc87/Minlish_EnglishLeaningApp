package com.minlish.app.ui.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.dao.UserStatsDao
import com.minlish.app.data.local.entity.UserStatsEntity
import com.minlish.app.domain.usecase.AppAnalytics
import com.minlish.app.domain.usecase.GetAnalyticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Badge(val id: String, val title: String, val icon: String, val description: String, val isUnlocked: Boolean)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userStatsDao: UserStatsDao,
    private val learningLogDao: LearningLogDao,
    private val databaseSeeder: com.minlish.app.data.local.DatabaseSeeder,
    private val getAnalyticsUseCase: GetAnalyticsUseCase,
    private val calculateStreakUseCase: com.minlish.app.domain.usecase.CalculateStreakUseCase
) : ViewModel() {

    private val userId = "current_user"

    val userStats: StateFlow<UserStatsEntity?> = userStatsDao.getUserStats(userId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _analytics = MutableStateFlow(AppAnalytics(0.0, 0.0, "Beginner (A1)", 0, 0, 0, 0))
    val analytics: StateFlow<AppAnalytics> = _analytics.asStateFlow()

    private val _badges = MutableStateFlow<List<Badge>>(emptyList())
    val badges: StateFlow<List<Badge>> = _badges.asStateFlow()

    var dailyActivity by mutableStateOf<List<LearningLogDao.DayCount>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            databaseSeeder.seedIfNecessary()
            calculateStreakUseCase(userId)
            loadDailyActivity()
            loadAnalytics()
        }
    }

    private fun loadAnalytics() {
        viewModelScope.launch {
            val result = getAnalyticsUseCase()
            _analytics.value = result
            
            // Sync mastered count back to UserStats
            val currentStats = userStats.value
            if (currentStats != null && currentStats.masteredCount != result.masteredCount) {
                userStatsDao.insertOrUpdateStats(currentStats.copy(masteredCount = result.masteredCount))
            }

            updateBadges(result)
        }
    }

    private fun updateBadges(analytics: AppAnalytics) {
        val stats = userStats.value ?: return
        val newBadges = listOf(
            Badge("1", "Người mới", "🌱", "Bắt đầu hành trình học tập", true),
            Badge("2", "Chăm chỉ", "🔥", "Streak đạt 3 ngày", stats.currentStreak >= 3),
            Badge("3", "Chinh phục", "🏆", "Độ chính xác > 80%", analytics.accuracy >= 80.0),
            Badge("4", "Bậc thầy", "👑", "Thành thạo 50 từ", stats.masteredCount >= 50)
        )
        _badges.value = newBadges
    }

    private fun loadDailyActivity() {
        viewModelScope.launch {
            val sevenDaysAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
            dailyActivity = learningLogDao.getDailyReviewCountsSince(sevenDaysAgo)
        }
    }
}
