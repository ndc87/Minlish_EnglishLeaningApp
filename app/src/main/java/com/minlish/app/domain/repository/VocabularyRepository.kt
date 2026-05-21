package com.minlish.app.domain.repository

import com.minlish.app.domain.model.Topic
import com.minlish.app.domain.model.Word
import com.minlish.app.domain.model.SrsLog
import kotlinx.coroutines.flow.Flow

interface VocabularyRepository {
    // Topic Operations
    fun getAllTopics(): Flow<List<Topic>>
    suspend fun getTopicById(topicId: String): Topic?
    suspend fun syncTopics()

    // Word Operations
    fun getWordsByTopic(topicId: String): Flow<List<Word>>
    suspend fun getWordById(wordId: String): Word?
    suspend fun syncWords()

    // SRS Operations
    fun getDueWords(): Flow<List<Word>>
    suspend fun getSrsLog(wordId: String): SrsLog?
    suspend fun updateSrsLog(srsLog: SrsLog)
}
