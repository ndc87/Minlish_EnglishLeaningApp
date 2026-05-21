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

    @Query("SELECT * FROM reviews")
    fun getAllReviews(): Flow<List<ReviewEntity>>

    @Query("""
        SELECT reviews.* FROM reviews 
        INNER JOIN cards ON reviews.cardId = cards.id 
        WHERE cards.topic = :topic AND reviews.nextDate <= :currentTime
    """)
    fun getDueReviewsByTopic(topic: String, currentTime: Long): Flow<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReview(review: ReviewEntity)

    @Query("DELETE FROM reviews")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM reviews WHERE nextDate > :currentTime")
    suspend fun getRetainedCount(currentTime: Long): Int

    @Query("SELECT COUNT(*) FROM reviews")
    suspend fun getTotalCardCount(): Int

    @Query("SELECT COUNT(*) FROM reviews WHERE repetitions > 0 AND nextDate > :currentTime AND interval < :masteredInterval")
    suspend fun getLearningCardCount(currentTime: Long, masteredInterval: Int): Int

    @Query("SELECT COUNT(*) FROM reviews WHERE nextDate <= :currentTime")
    suspend fun getDueCardCount(currentTime: Long): Int

    @Query("SELECT COUNT(*) FROM reviews WHERE interval >= :masteredInterval")
    suspend fun getMasteredCardCount(masteredInterval: Int): Int

    @Query("""
        SELECT cards.* FROM cards 
        INNER JOIN reviews ON cards.id = reviews.cardId 
        WHERE reviews.repetitions > 0
    """)
    fun getLearnedCards(): Flow<List<com.minlish.app.data.local.entity.CardEntity>>
}

