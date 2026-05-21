package com.minlish.app.ui.learning;

import com.minlish.app.domain.repository.VocabularyRepository;
import com.minlish.app.domain.usecase.GetDueWordsUseCase;
import com.minlish.app.domain.usecase.UpdateSrsLogicUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class LearningViewModel_Factory implements Factory<LearningViewModel> {
  private final Provider<GetDueWordsUseCase> getDueWordsUseCaseProvider;

  private final Provider<UpdateSrsLogicUseCase> updateSrsLogicUseCaseProvider;

  private final Provider<VocabularyRepository> repositoryProvider;

  public LearningViewModel_Factory(Provider<GetDueWordsUseCase> getDueWordsUseCaseProvider,
      Provider<UpdateSrsLogicUseCase> updateSrsLogicUseCaseProvider,
      Provider<VocabularyRepository> repositoryProvider) {
    this.getDueWordsUseCaseProvider = getDueWordsUseCaseProvider;
    this.updateSrsLogicUseCaseProvider = updateSrsLogicUseCaseProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public LearningViewModel get() {
    return newInstance(getDueWordsUseCaseProvider.get(), updateSrsLogicUseCaseProvider.get(), repositoryProvider.get());
  }

  public static LearningViewModel_Factory create(
      Provider<GetDueWordsUseCase> getDueWordsUseCaseProvider,
      Provider<UpdateSrsLogicUseCase> updateSrsLogicUseCaseProvider,
      Provider<VocabularyRepository> repositoryProvider) {
    return new LearningViewModel_Factory(getDueWordsUseCaseProvider, updateSrsLogicUseCaseProvider, repositoryProvider);
  }

  public static LearningViewModel newInstance(GetDueWordsUseCase getDueWordsUseCase,
      UpdateSrsLogicUseCase updateSrsLogicUseCase, VocabularyRepository repository) {
    return new LearningViewModel(getDueWordsUseCase, updateSrsLogicUseCase, repository);
  }
}
