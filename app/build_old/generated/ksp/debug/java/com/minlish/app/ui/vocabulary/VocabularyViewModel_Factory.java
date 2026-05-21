package com.minlish.app.ui.vocabulary;

import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.domain.usecase.ExportImportUseCase;
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
public final class VocabularyViewModel_Factory implements Factory<VocabularyViewModel> {
  private final Provider<CardDao> cardDaoProvider;

  private final Provider<ExportImportUseCase> exportImportUseCaseProvider;

  public VocabularyViewModel_Factory(Provider<CardDao> cardDaoProvider,
      Provider<ExportImportUseCase> exportImportUseCaseProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.exportImportUseCaseProvider = exportImportUseCaseProvider;
  }

  @Override
  public VocabularyViewModel get() {
    return newInstance(cardDaoProvider.get(), exportImportUseCaseProvider.get());
  }

  public static VocabularyViewModel_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<ExportImportUseCase> exportImportUseCaseProvider) {
    return new VocabularyViewModel_Factory(cardDaoProvider, exportImportUseCaseProvider);
  }

  public static VocabularyViewModel newInstance(CardDao cardDao,
      ExportImportUseCase exportImportUseCase) {
    return new VocabularyViewModel(cardDao, exportImportUseCase);
  }
}
