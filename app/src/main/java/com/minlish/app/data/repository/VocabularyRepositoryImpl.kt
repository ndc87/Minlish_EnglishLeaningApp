package com.minlish.app.data.repository

import com.minlish.app.data.local.dao.TopicDao
import com.minlish.app.data.local.dao.WordDao
import com.minlish.app.data.local.entity.SrsLogEntity
import com.minlish.app.data.local.entity.TopicEntity
import com.minlish.app.data.local.entity.WordEntity
import com.minlish.app.data.remote.api.VocabularyApiService
import com.minlish.app.domain.model.SrsLog
import com.minlish.app.domain.model.Topic
import com.minlish.app.domain.model.Word
import com.minlish.app.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VocabularyRepositoryImpl @Inject constructor(
    private val topicDao: TopicDao,
    private val wordDao: WordDao,
    private val apiService: VocabularyApiService
) : VocabularyRepository {

    override fun getAllTopics(): Flow<List<Topic>> {
        return topicDao.getAllTopics().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getTopicById(topicId: String): Topic? {
        return topicDao.getTopicById(topicId)?.toDomain()
    }

    override suspend fun syncTopics() {
        try {
            val remoteTopics = apiService.getTopics()
            topicDao.insertTopics(remoteTopics.map { 
                TopicEntity(it.id, it.name, it.description, it.tags.joinToString(",")) 
            })
        } catch (e: Exception) {
            // Handle network exceptions or let it fail gracefully
        }
    }

    override fun getWordsByTopic(topicId: String): Flow<List<Word>> {
        return wordDao.getWordsByTopic(topicId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getWordById(wordId: String): Word? {
        return wordDao.getWordById(wordId)?.toDomain()
    }

    override suspend fun syncWords() {
        try {
            val remoteWords = apiService.getWords()
            wordDao.insertWords(remoteWords.map { 
                WordEntity(
                    id = it.id,
                    topicId = it.topicId,
                    word = it.word,
                    pronunciation = it.pronunciation,
                    meaning = it.meaning,
                    descriptionEn = it.descriptionEn,
                    example = it.example,
                    collocation = it.collocation,
                    relatedWords = it.relatedWords.joinToString(","),
                    note = it.note
                ) 
            })
        } catch (e: Exception) {
            // Handle network exceptions
        }
    }

    override fun getDueWords(): Flow<List<Word>> {
        return wordDao.getDueWords(System.currentTimeMillis()).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getSrsLog(wordId: String): SrsLog? {
        return wordDao.getSrsLog(wordId)?.toDomain()
    }

    override suspend fun updateSrsLog(srsLog: SrsLog) {
        wordDao.insertSrsLog(
            SrsLogEntity(
                wordId = srsLog.wordId,
                interval = srsLog.interval,
                easeFactor = srsLog.easeFactor,
                repetitionCount = srsLog.repetitionCount,
                nextReviewDate = srsLog.nextReviewDate
            )
        )
    }

    // Extension Mappers
    private fun TopicEntity.toDomain() = Topic(
        id = id,
        name = name,
        description = description,
        tags = tags.split(",").filter { it.isNotBlank() }
    )

    private fun WordEntity.toDomain() = Word(
        id = id,
        topicId = topicId,
        word = word,
        pronunciation = pronunciation,
        meaning = meaning,
        descriptionEn = descriptionEn,
        example = example,
        collocation = collocation,
        relatedWords = relatedWords.split(",").filter { it.isNotBlank() },
        note = note
    )

    private fun SrsLogEntity.toDomain() = SrsLog(
        wordId = wordId,
        interval = interval,
        easeFactor = easeFactor,
        repetitionCount = repetitionCount,
        nextReviewDate = nextReviewDate
    )
}
