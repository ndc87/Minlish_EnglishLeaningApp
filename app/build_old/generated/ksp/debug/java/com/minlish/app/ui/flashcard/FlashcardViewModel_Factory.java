package com.minlish.app.ui.flashcard;

import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.ReviewDao;
import com.minlish.app.data.local.dao.UserStatsDao;
import com.minlish.app.domain.usecase.CalculateSM2IntervalUseCase;
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
public final class FlashcardViewModel_Factory implements Factory<FlashcardViewModel> {
  private final Provider<CardDao> cardDaoProvider;

  private final Provider<ReviewDao> reviewDaoProvider;

  private final Provider<LearningLogDao> learningLogDaoProvider;

  private final Provider<UserStatsDao> userStatsDaoProvider;

  private final Provider<CalculateSM2IntervalUseCase> calculateSM2Provider;

  public FlashcardViewModel_Factory(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider, Provider<LearningLogDao> learningLogDaoProvider,
      Provider<UserStatsDao> userStatsDaoProvider,
      Provider<CalculateSM2IntervalUseCase> calculateSM2Provider) {
    this.cardDaoProvider = cardDaoProvider;
    this.reviewDaoProvider = reviewDaoProvider;
    this.learningLogDaoProvider = learningLogDaoProvider;
    this.userStatsDaoProvider = userStatsDaoProvider;
    this.calculateSM2Provider = calculateSM2Provider;
  }

  @Override
  public FlashcardViewModel get() {
    return newInstance(cardDaoProvider.get(), reviewDaoProvider.get(), learningLogDaoProvider.get(), userStatsDaoProvider.get(), calculateSM2Provider.get());
  }

  public static FlashcardViewModel_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider, Provider<LearningLogDao> learningLogDaoProvider,
      Provider<UserStatsDao> userStatsDaoProvider,
      Provider<CalculateSM2IntervalUseCase> calculateSM2Provider) {
    return new FlashcardViewModel_Factory(cardDaoProvider, reviewDaoProvider, learningLogDaoProvider, userStatsDaoProvider, calculateSM2Provider);
  }

  public static FlashcardViewModel newInstance(CardDao cardDao, ReviewDao reviewDao,
      LearningLogDao learningLogDao, UserStatsDao userStatsDao,
      CalculateSM2IntervalUseCase calculateSM2) {
    return new FlashcardViewModel(cardDao, reviewDao, learningLogDao, userStatsDao, calculateSM2);
  }
}
