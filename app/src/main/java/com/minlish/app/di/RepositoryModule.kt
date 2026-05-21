package com.minlish.app.di

import com.minlish.app.data.repository.AuthRepositoryImpl
import com.minlish.app.data.repository.VocabularyRepositoryImpl
import com.minlish.app.domain.repository.AuthRepository
import com.minlish.app.domain.repository.VocabularyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindVocabularyRepository(
        vocabularyRepositoryImpl: VocabularyRepositoryImpl
    ): VocabularyRepository
}
