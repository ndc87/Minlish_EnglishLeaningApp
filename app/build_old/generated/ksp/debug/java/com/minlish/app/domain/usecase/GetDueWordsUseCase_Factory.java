package com.minlish.app.domain.usecase;

import com.minlish.app.domain.repository.VocabularyRepository;
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
public final class GetDueWordsUseCase_Factory implements Factory<GetDueWordsUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public GetDueWordsUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetDueWordsUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetDueWordsUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new GetDueWordsUseCase_Factory(repositoryProvider);
  }

  public static GetDueWordsUseCase newInstance(VocabularyRepository repository) {
    return new GetDueWordsUseCase(repository);
  }
}
