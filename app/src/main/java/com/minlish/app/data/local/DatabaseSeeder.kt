package com.minlish.app.data.local

import com.minlish.app.data.local.dao.CardDao
import com.minlish.app.data.local.dao.ReviewDao
import com.minlish.app.data.local.dao.LearningLogDao
import com.minlish.app.data.local.entity.CardEntity
import com.minlish.app.data.local.entity.UserStatsEntity
import com.minlish.app.data.local.storage.EncryptedAuthStorage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSeeder @Inject constructor(
    private val cardDao: CardDao,
    private val reviewDao: ReviewDao,
    private val learningLogDao: LearningLogDao,
    private val userStatsDao: com.minlish.app.data.local.dao.UserStatsDao,
    private val authStorage: EncryptedAuthStorage,
    private val csvWordProvider: CsvWordProvider
) {

    private val mutex = Mutex()

    suspend fun seedIfNecessary() = withContext(Dispatchers.IO) {
        mutex.withLock {
            val cardsCount = cardDao.getAllCardsList().size
            val reviewsCount = reviewDao.getTotalCardCount()

            if (cardsCount == 0 || reviewsCount == 0) {
                seedToeicVocabulary()
            } else {
                // Ensure user stats exist even if cards were already there
                val stats = userStatsDao.getUserStatsOnce("current_user")
                if (stats == null) {
                    userStatsDao.insertOrUpdateStats(
                        UserStatsEntity("current_user", 0, 0, 0, 10)
                    )
                }
            }
        }
    }


    private suspend fun seedToeicVocabulary() {
        val toeicWords = csvWordProvider.loadWordsFromCsv("toeic_600_words.csv")

        if (toeicWords.isEmpty()) {
            android.util.Log.e("DatabaseSeeder", "No words found in CSV to seed!")
            return
        }

        // Clear existing to avoid duplicates during re-seed
        cardDao.deleteAll()
        reviewDao.deleteAll()
        learningLogDao.deleteAll()
        
        toeicWords.forEach { card ->
            cardDao.insertCardWithReview(card, reviewDao)
        }

        // Initialize mock user stats so Dashboard is not empty
        userStatsDao.insertOrUpdateStats(
            UserStatsEntity("current_user", 0, 0, 0, 10)
        )
        
        android.util.Log.d("DatabaseSeeder", "Seeded ${toeicWords.size} words from CSV")
    }
}
