package com.minlish.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.minlish.app.data.local.entity.LearningLogEntity

@Dao
interface LearningLogDao {
    @Insert
    suspend fun insertLog(log: LearningLogEntity)

    @Query("SELECT * FROM learning_logs WHERE cardId = :cardId")
    suspend fun getLogsByCardId(cardId: Long): List<LearningLogEntity>

    @Query("SELECT COUNT(*) FROM learning_logs WHERE timestamp >= :since")
    suspend fun getReviewCountSince(since: Long): Int

    @Query("SELECT COUNT(*) FROM learning_logs WHERE qualityRating >= 3")
    suspend fun getCorrectReviewCount(): Int

    @Query("SELECT COUNT(*) FROM learning_logs")
    suspend fun getTotalReviewCount(): Int

    @Query("DELETE FROM learning_logs")
    suspend fun deleteAll()


    @Query("""
        SELECT date(timestamp/1000, 'unixepoch') as date, COUNT(*) as count 
        FROM learning_logs 
        WHERE timestamp >= :since 
        GROUP BY date 
        ORDER BY date ASC
    """)
    suspend fun getDailyReviewCountsSince(since: Long): List<DayCount>

    data class DayCount(val date: String, val count: Int)
}
