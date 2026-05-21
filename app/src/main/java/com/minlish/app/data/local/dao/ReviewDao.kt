package com.minlish.app.data.local.dao

import androidx.room.*
import com.minlish.app.data.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviews WHERE cardId = :cardId")
    suspend fun getReviewByCardId(cardId: Long): ReviewEntity?

    @Query("SELECT * FROM reviews WHERE nextDate <= :currentTime")
    fun getDueReviews(currentTime: Long): Flow<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReview(review: ReviewEntity)

    @Query("DELETE FROM reviews")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM reviews WHERE nextDate > :currentTime")
    suspend fun getRetainedCount(currentTime: Long): Int

    @Query("SELECT COUNT(*) FROM reviews")
    suspend fun getTotalCardCount(): Int
}

