package com.minlish.app.domain.usecase

import com.minlish.app.data.local.dao.CardDao
import com.minlish.app.data.local.dao.ReviewDao
import com.minlish.app.data.local.entity.CardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddNewWordUseCase @Inject constructor(
    private val cardDao: CardDao,
    private val reviewDao: ReviewDao
) {
    suspend operator fun invoke(
        word: String,
        pos: String,
        meaning: String,
        example: String,
        pronunciation: String? = null,
        descriptionEn: String? = null,
        topic: String = "General"
    ): Result<Unit> {
        return try {
            val card = CardEntity(
                word = word.trim(),
                pos = pos.trim().lowercase(),
                meaning = meaning.trim(),
                example = example.trim(),
                pronunciation = pronunciation,
                descriptionEn = descriptionEn,
                topic = topic,
                audioUrl = null,
                imageUrl = null,
                level = 1
            )
            cardDao.insertCardWithReview(card, reviewDao)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}
