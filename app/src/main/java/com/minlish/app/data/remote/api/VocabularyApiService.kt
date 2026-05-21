package com.minlish.app.data.remote.api

import com.minlish.app.data.remote.dto.TopicDto
import com.minlish.app.data.remote.dto.WordDto
import retrofit2.http.GET
import retrofit2.http.Query

interface VocabularyApiService {
    @GET("api/topics")
    suspend fun getTopics(@Query("lastSyncDate") lastSyncDate: Long? = null): List<TopicDto>

    @GET("api/words")
    suspend fun getWords(
        @Query("topicId") topicId: String? = null,
        @Query("lastSyncDate") lastSyncDate: Long? = null
    ): List<WordDto>
}
