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
public final class UpdateSrsLogicUseCase_Factory implements Factory<UpdateSrsLogicUseCase> {
  private final Provider<VocabularyRepository> repositoryProvider;

  public UpdateSrsLogicUseCase_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateSrsLogicUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateSrsLogicUseCase_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new UpdateSrsLogicUseCase_Factory(repositoryProvider);
  }

  public static UpdateSrsLogicUseCase newInstance(VocabularyRepository repository) {
    return new UpdateSrsLogicUseCase(repository);
  }
}
