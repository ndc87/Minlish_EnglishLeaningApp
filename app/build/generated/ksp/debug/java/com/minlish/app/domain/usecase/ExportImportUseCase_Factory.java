package com.minlish.app.domain.usecase;

import android.content.Context;
import com.minlish.app.data.local.dao.CardDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ExportImportUseCase_Factory implements Factory<ExportImportUseCase> {
  private final Provider<CardDao> cardDaoProvider;

  private final Provider<Context> contextProvider;

  public ExportImportUseCase_Factory(Provider<CardDao> cardDaoProvider,
      Provider<Context> contextProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ExportImportUseCase get() {
    return newInstance(cardDaoProvider.get(), contextProvider.get());
  }

  public static ExportImportUseCase_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<Context> contextProvider) {
    return new ExportImportUseCase_Factory(cardDaoProvider, contextProvider);
  }

  public static ExportImportUseCase newInstance(CardDao cardDao, Context context) {
    return new ExportImportUseCase(cardDao, context);
  }
}
