package com.minlish.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.minlish.app.data.local.dao.*
import com.minlish.app.data.local.entity.*

@Database(
    entities = [
        CardEntity::class,
        ReviewEntity::class,
        LearningLogEntity::class,
        UserStatsEntity::class,
        TopicEntity::class,
        WordEntity::class,
        UserEntity::class,
        StudyLogEntity::class,
        SrsLogEntity::class
    ],
    version = 7,

    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun reviewDao(): ReviewDao
    abstract fun learningLogDao(): LearningLogDao
    abstract fun userStatsDao(): UserStatsDao
    abstract fun topicDao(): TopicDao
    abstract fun wordDao(): WordDao
    abstract fun userDao(): UserDao
    abstract fun studyLogDao(): StudyLogDao

    companion object {
        const val DATABASE_NAME = "minlish_db"
    }
}
