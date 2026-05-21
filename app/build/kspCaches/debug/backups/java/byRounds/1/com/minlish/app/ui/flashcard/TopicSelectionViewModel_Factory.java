package com.minlish.app.ui.flashcard;

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
public final class TopicSelectionViewModel_Factory implements Factory<TopicSelectionViewModel> {
  private final Provider<CardDao> cardDaoProvider;

  public TopicSelectionViewModel_Factory(Provider<CardDao> cardDaoProvider) {
    this.cardDaoProvider = cardDaoProvider;
  }

  @Override
  public TopicSelectionViewModel get() {
    return newInstance(cardDaoProvider.get());
  }

  public static TopicSelectionViewModel_Factory create(Provider<CardDao> cardDaoProvider) {
    return new TopicSelectionViewModel_Factory(cardDaoProvider);
  }

  public static TopicSelectionViewModel newInstance(CardDao cardDao) {
    return new TopicSelectionViewModel(cardDao);
  }
}
