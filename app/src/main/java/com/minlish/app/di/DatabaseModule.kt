package com.minlish.app.di

import android.content.Context
import androidx.room.Room
import com.minlish.app.data.local.AppDatabase
import com.minlish.app.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideCardDao(db: AppDatabase): CardDao = db.cardDao()

    @Provides
    fun provideReviewDao(db: AppDatabase): ReviewDao = db.reviewDao()

    @Provides
    fun provideLearningLogDao(db: AppDatabase): LearningLogDao = db.learningLogDao()

    @Provides
    fun provideUserStatsDao(db: AppDatabase): UserStatsDao = db.userStatsDao()

    @Provides
    fun provideTopicDao(db: AppDatabase): TopicDao = db.topicDao()

    @Provides
    fun provideWordDao(db: AppDatabase): WordDao = db.wordDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideStudyLogDao(db: AppDatabase): StudyLogDao = db.studyLogDao()
}
