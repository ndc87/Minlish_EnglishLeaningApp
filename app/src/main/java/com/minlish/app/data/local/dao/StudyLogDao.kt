package com.minlish.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.minlish.app.data.local.entity.StudyLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyLogDao {
    @Insert
    suspend fun insertLog(log: StudyLogEntity)

    @Query("SELECT * FROM study_logs ORDER BY dateTimestamp DESC")
    fun getAllLogs(): Flow<List<StudyLogEntity>>

    @Query("SELECT * FROM study_logs WHERE dateTimestamp >= :startOfDay AND dateTimestamp < :endOfDay")
    suspend fun getLogsForDay(startOfDay: Long, endOfDay: Long): List<StudyLogEntity>
}
