package com.minlish.app.ui.practice;

import com.minlish.app.data.local.dao.CardDao;
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
public final class PracticeViewModel_Factory implements Factory<PracticeViewModel> {
  private final Provider<CardDao> cardDaoProvider;

  public PracticeViewModel_Factory(Provider<CardDao> cardDaoProvider) {
    this.cardDaoProvider = cardDaoProvider;
  }

  @Override
  public PracticeViewModel get() {
    return newInstance(cardDaoProvider.get());
  }

  public static PracticeViewModel_Factory create(Provider<CardDao> cardDaoProvider) {
    return new PracticeViewModel_Factory(cardDaoProvider);
  }

  public static PracticeViewModel newInstance(CardDao cardDao) {
    return new PracticeViewModel(cardDao);
  }
}
