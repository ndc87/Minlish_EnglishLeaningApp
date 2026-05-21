package com.minlish.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.minlish.app.data.local.entity.UserStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserStatsDao {
    @Query("SELECT * FROM user_stats WHERE userId = :userId")
    fun getUserStats(userId: String): Flow<UserStatsEntity?>

    @Query("SELECT * FROM user_stats WHERE userId = :userId")
    suspend fun getUserStatsOnce(userId: String): UserStatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateStats(stats: UserStatsEntity)
}
