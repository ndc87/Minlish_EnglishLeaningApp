package com.minlish.app.domain.usecase;

import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.UserStatsDao;
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
public final class CalculateStreakUseCase_Factory implements Factory<CalculateStreakUseCase> {
  private final Provider<LearningLogDao> learningLogDaoProvider;

  private final Provider<UserStatsDao> userStatsDaoProvider;

  public CalculateStreakUseCase_Factory(Provider<LearningLogDao> learningLogDaoProvider,
      Provider<UserStatsDao> userStatsDaoProvider) {
    this.learningLogDaoProvider = learningLogDaoProvider;
    this.userStatsDaoProvider = userStatsDaoProvider;
  }

  @Override
  public CalculateStreakUseCase get() {
    return newInstance(learningLogDaoProvider.get(), userStatsDaoProvider.get());
  }

  public static CalculateStreakUseCase_Factory create(
      Provider<LearningLogDao> learningLogDaoProvider,
      Provider<UserStatsDao> userStatsDaoProvider) {
    return new CalculateStreakUseCase_Factory(learningLogDaoProvider, userStatsDaoProvider);
  }

  public static CalculateStreakUseCase newInstance(LearningLogDao learningLogDao,
      UserStatsDao userStatsDao) {
    return new CalculateStreakUseCase(learningLogDao, userStatsDao);
  }
}
