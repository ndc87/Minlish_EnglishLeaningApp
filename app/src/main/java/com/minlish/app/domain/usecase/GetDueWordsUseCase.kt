package com.minlish.app.domain.usecase

import com.minlish.app.domain.model.Word
import com.minlish.app.domain.repository.VocabularyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDueWordsUseCase @Inject constructor(
    private val repository: VocabularyRepository
) {
    operator fun invoke(): Flow<List<Word>> {
        return repository.getDueWords()
    }
}
