package com.minlish.app.domain.usecase;

import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.data.local.dao.ReviewDao;
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
public final class AddNewWordUseCase_Factory implements Factory<AddNewWordUseCase> {
  private final Provider<CardDao> cardDaoProvider;

  private final Provider<ReviewDao> reviewDaoProvider;

  public AddNewWordUseCase_Factory(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.reviewDaoProvider = reviewDaoProvider;
  }

  @Override
  public AddNewWordUseCase get() {
    return newInstance(cardDaoProvider.get(), reviewDaoProvider.get());
  }

  public static AddNewWordUseCase_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider) {
    return new AddNewWordUseCase_Factory(cardDaoProvider, reviewDaoProvider);
  }

  public static AddNewWordUseCase newInstance(CardDao cardDao, ReviewDao reviewDao) {
    return new AddNewWordUseCase(cardDao, reviewDao);
  }
}
