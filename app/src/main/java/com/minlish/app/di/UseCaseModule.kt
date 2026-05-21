package com.minlish.app.di

import com.minlish.app.domain.usecase.CalculateSM2IntervalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCalculateSM2IntervalUseCase(): CalculateSM2IntervalUseCase {
        return CalculateSM2IntervalUseCase()
    }
}
