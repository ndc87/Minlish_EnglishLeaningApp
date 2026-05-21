package com.minlish.app.ui.dashboard;

import com.minlish.app.data.local.DatabaseSeeder;
import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.UserStatsDao;
import com.minlish.app.domain.usecase.CalculateStreakUseCase;
import com.minlish.app.domain.usecase.GetAnalyticsUseCase;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<UserStatsDao> userStatsDaoProvider;

  private final Provider<LearningLogDao> learningLogDaoProvider;

  private final Provider<DatabaseSeeder> databaseSeederProvider;

  private final Provider<GetAnalyticsUseCase> getAnalyticsUseCaseProvider;

  private final Provider<CalculateStreakUseCase> calculateStreakUseCaseProvider;

  public DashboardViewModel_Factory(Provider<UserStatsDao> userStatsDaoProvider,
      Provider<LearningLogDao> learningLogDaoProvider,
      Provider<DatabaseSeeder> databaseSeederProvider,
      Provider<GetAnalyticsUseCase> getAnalyticsUseCaseProvider,
      Provider<CalculateStreakUseCase> calculateStreakUseCaseProvider) {
    this.userStatsDaoProvider = userStatsDaoProvider;
    this.learningLogDaoProvider = learningLogDaoProvider;
    this.databaseSeederProvider = databaseSeederProvider;
    this.getAnalyticsUseCaseProvider = getAnalyticsUseCaseProvider;
    this.calculateStreakUseCaseProvider = calculateStreakUseCaseProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(userStatsDaoProvider.get(), learningLogDaoProvider.get(), databaseSeederProvider.get(), getAnalyticsUseCaseProvider.get(), calculateStreakUseCaseProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<UserStatsDao> userStatsDaoProvider,
      Provider<LearningLogDao> learningLogDaoProvider,
      Provider<DatabaseSeeder> databaseSeederProvider,
      Provider<GetAnalyticsUseCase> getAnalyticsUseCaseProvider,
      Provider<CalculateStreakUseCase> calculateStreakUseCaseProvider) {
    return new DashboardViewModel_Factory(userStatsDaoProvider, learningLogDaoProvider, databaseSeederProvider, getAnalyticsUseCaseProvider, calculateStreakUseCaseProvider);
  }

  public static DashboardViewModel newInstance(UserStatsDao userStatsDao,
      LearningLogDao learningLogDao, DatabaseSeeder databaseSeeder,
      GetAnalyticsUseCase getAnalyticsUseCase, CalculateStreakUseCase calculateStreakUseCase) {
    return new DashboardViewModel(userStatsDao, learningLogDao, databaseSeeder, getAnalyticsUseCase, calculateStreakUseCase);
  }
}
