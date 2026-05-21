package com.minlish.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.minlish.app.data.local.entity.SrsLogEntity
import com.minlish.app.data.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE topicId = :topicId")
    fun getWordsByTopic(topicId: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE id = :wordId")
    suspend fun getWordById(wordId: String): WordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<WordEntity>)

    @Query("DELETE FROM words")
    suspend fun clearAllWords()

    // SRS Operations
    @Query("SELECT * FROM srs_logs WHERE wordId = :wordId")
    suspend fun getSrsLog(wordId: String): SrsLogEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSrsLog(srsLog: SrsLogEntity)
    
    @Query("SELECT words.* FROM words INNER JOIN srs_logs ON words.id = srs_logs.wordId WHERE srs_logs.nextReviewDate <= :currentTimestamp")
    fun getDueWords(currentTimestamp: Long): Flow<List<WordEntity>>

    @Query("SELECT words.* FROM words WHERE id NOT IN (SELECT wordId FROM srs_logs)")
    fun getNewWords(): Flow<List<WordEntity>>
}
