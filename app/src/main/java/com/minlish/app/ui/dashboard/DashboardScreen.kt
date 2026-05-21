package com.minlish.app.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.tooling.preview.Preview
import com.minlish.app.ui.theme.MinLishTheme
import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.entity.UserStatsEntity

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val stats by viewModel.userStats.collectAsState()
    val analytics by viewModel.analytics.collectAsState()
    val badges by viewModel.badges.collectAsState()
    
    DashboardContent(
        stats = stats,
        analytics = analytics,
        badges = badges,
        dailyActivity = viewModel.dailyActivity
    )
}

@Composable
fun DashboardContent(
    stats: UserStatsEntity?,
    analytics: com.minlish.app.domain.usecase.AppAnalytics,
    badges: List<Badge>,
    dailyActivity: List<LearningLogDao.DayCount>
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MinLish Dashboard",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        stats?.let {
            // Badges Section
            Text(
                text = "Danh hiệu của bạn",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                badges.forEach { badge ->
                    BadgeItem(badge)
                }
            }

            // Accuracy & Retention Details
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    StatCard(label = "Accuracy", value = "${analytics.accuracy.toInt()}%", icon = "🎯", color = Color(0xFF2196F3))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    StatCard(label = "Retention", value = "${analytics.retentionRate.toInt()}%", icon = "🧠", color = Color(0xFF9C27B0))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            StatCard(label = "Current Streak", value = "${it.currentStreak} Days", icon = "🔥", color = Color(0xFFFF5722))
            Spacer(modifier = Modifier.height(16.dp))
            StatCard(label = "Total XP", value = "${it.totalXp} XP", icon = "⭐", color = Color(0xFFFFC107))

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1f)) {
                    StatCard(label = "Đã học", value = "${analytics.learnedCount}", icon = "📖", color = Color(0xFF2196F3))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    StatCard(label = "Cần ôn", value = "${analytics.dueCount}", icon = "⏰", color = Color(0xFFFF9800))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            StatCard(label = "Level Estimation", value = analytics.levelEstimation, icon = "📈", color = Color(0xFF4CAF50))
            
            Spacer(modifier = Modifier.height(32.dp))
            DailyGoalProgress(current = analytics.todayCount, goal = it.dailyGoal)
        } ?: run {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Activity (Last 7 Days)",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActivityChart(data = dailyActivity)
    }
}

@Composable
fun BadgeItem(badge: Badge) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp).alpha(if (badge.isUnlocked) 1f else 0.3f)
    ) {
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(64.dp),
            shadowElevation = 2.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(text = badge.icon, fontSize = 32.sp)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = badge.title,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    MinLishTheme {
        DashboardContent(
            stats = UserStatsEntity("user", 7, 1250, 42, 10),
            analytics = com.minlish.app.domain.usecase.AppAnalytics(85.0, 72.0, "Intermediate (B1)", 120, 15, 8, 42),
            badges = listOf(
                Badge("1", "Người mới", "🌱", "", true),
                Badge("2", "Chăm chỉ", "🔥", "", false)
            ),
            dailyActivity = listOf(
                LearningLogDao.DayCount("Mon", 5),
                LearningLogDao.DayCount("Tue", 8),
                LearningLogDao.DayCount("Wed", 3),
                LearningLogDao.DayCount("Thu", 10),
                LearningLogDao.DayCount("Fri", 12),
                LearningLogDao.DayCount("Sat", 4),
                LearningLogDao.DayCount("Sun", 7)
            )
        )
    }
}



@Composable
fun ActivityChart(data: List<LearningLogDao.DayCount>) {
    val maxCount = (data.maxByOrNull { it.count }?.count ?: 10).coerceAtLeast(1)
    
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val barWidth = size.width / 14f
        val spaceWidth = size.width / 14f
        
        data.forEachIndexed { index, dayCount ->
            val barHeight = (dayCount.count.toFloat() / maxCount.toFloat()) * size.height
            val x = (index * (barWidth + spaceWidth)) + spaceWidth / 2
            
            drawRect(
                color = Color(0xFF2196F3),
                topLeft = Offset(x, size.height - barHeight),
                size = Size(barWidth, barHeight)
            )
        }
        
        // Baseline
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height),
            end = Offset(size.width, size.height),
            strokeWidth = 2f
        )
    }
}

@Composable
fun StatCard(label: String, value: String, icon: String, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = label, fontSize = 14.sp, color = Color.Gray)
                Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = color)
            }
            Text(text = icon, fontSize = 32.sp)
        }
    }
}

@Composable
fun DailyGoalProgress(current: Int, goal: Int) {
    val progress = if (goal > 0) current.toFloat() / goal.toFloat() else 0f
    
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Daily Goal", fontWeight = FontWeight.Medium)
            Text(text = "$current / $goal words")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            color = Color(0xFF2196F3),
            trackColor = Color(0xFFE3F2FD)
        )
    }
}
