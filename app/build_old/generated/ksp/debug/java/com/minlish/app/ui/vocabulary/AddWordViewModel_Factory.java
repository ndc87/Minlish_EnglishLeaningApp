package com.minlish.app.ui.vocabulary;

import com.minlish.app.domain.usecase.AddNewWordUseCase;
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
public final class AddWordViewModel_Factory implements Factory<AddWordViewModel> {
  private final Provider<AddNewWordUseCase> addNewWordUseCaseProvider;

  public AddWordViewModel_Factory(Provider<AddNewWordUseCase> addNewWordUseCaseProvider) {
    this.addNewWordUseCaseProvider = addNewWordUseCaseProvider;
  }

  @Override
  public AddWordViewModel get() {
    return newInstance(addNewWordUseCaseProvider.get());
  }

  public static AddWordViewModel_Factory create(
      Provider<AddNewWordUseCase> addNewWordUseCaseProvider) {
    return new AddWordViewModel_Factory(addNewWordUseCaseProvider);
  }

  public static AddWordViewModel newInstance(AddNewWordUseCase addNewWordUseCase) {
    return new AddWordViewModel(addNewWordUseCase);
  }
}
