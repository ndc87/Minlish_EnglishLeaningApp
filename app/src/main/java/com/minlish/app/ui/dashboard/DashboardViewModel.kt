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

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userStatsDao: UserStatsDao,
    private val learningLogDao: LearningLogDao,
    private val databaseSeeder: com.minlish.app.data.local.DatabaseSeeder,
    private val getAnalyticsUseCase: GetAnalyticsUseCase
) : ViewModel() {

    private val userId = "current_user"

    val userStats: StateFlow<UserStatsEntity?> = userStatsDao.getUserStats(userId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    private val _analytics = MutableStateFlow(AppAnalytics(0.0, 0.0, "Beginner (A1)"))
    val analytics: StateFlow<AppAnalytics> = _analytics.asStateFlow()

    var dailyActivity by mutableStateOf<List<LearningLogDao.DayCount>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            databaseSeeder.seedIfNecessary()
            loadDailyActivity()
            loadAnalytics()
        }
    }

    private fun loadAnalytics() {
        viewModelScope.launch {
            _analytics.value = getAnalyticsUseCase()
        }
    }



    private fun loadDailyActivity() {
        viewModelScope.launch {
            val sevenDaysAgo = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)
            dailyActivity = learningLogDao.getDailyReviewCountsSince(sevenDaysAgo)
        }
    }
}
