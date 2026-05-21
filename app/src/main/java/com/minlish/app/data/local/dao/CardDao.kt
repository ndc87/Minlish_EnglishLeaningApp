package com.minlish.app.data.local.dao

import androidx.room.*
import com.minlish.app.data.local.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardEntity>>

    @Query("SELECT * FROM cards WHERE id = :id")
    suspend fun getCardById(id: Long): CardEntity?

    @Query("SELECT DISTINCT topic FROM cards")
    fun getAllTopics(): Flow<List<String>>

    @Query("DELETE FROM cards")
    suspend fun deleteAll()

    @Query("SELECT * FROM cards")
    suspend fun getAllCardsList(): List<CardEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(cards: List<CardEntity>)

    @Query("SELECT * FROM cards ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCard(): CardEntity?

    @Update
    suspend fun updateCard(card: CardEntity)

    @Delete
    suspend fun deleteCard(card: CardEntity)

    @Transaction
    suspend fun insertCardWithReview(card: CardEntity, reviewDao: ReviewDao) {
        val cardId = insertCard(card)
        val initialReview = com.minlish.app.data.local.entity.ReviewEntity(
            userId = "current_user",
            cardId = cardId,
            repetitions = 0,
            interval = 0,
            easeFactor = 2.5,
            lastDate = System.currentTimeMillis(),
            nextDate = System.currentTimeMillis() - 60000 // Force due now (1 min ago)
        )
        reviewDao.insertOrUpdateReview(initialReview)
    }
}
