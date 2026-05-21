package com.minlish.app.ui.dashboard;

import com.minlish.app.data.local.DatabaseSeeder;
import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.UserStatsDao;
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

  public DashboardViewModel_Factory(Provider<UserStatsDao> userStatsDaoProvider,
      Provider<LearningLogDao> learningLogDaoProvider,
      Provider<DatabaseSeeder> databaseSeederProvider,
      Provider<GetAnalyticsUseCase> getAnalyticsUseCaseProvider) {
    this.userStatsDaoProvider = userStatsDaoProvider;
    this.learningLogDaoProvider = learningLogDaoProvider;
    this.databaseSeederProvider = databaseSeederProvider;
    this.getAnalyticsUseCaseProvider = getAnalyticsUseCaseProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(userStatsDaoProvider.get(), learningLogDaoProvider.get(), databaseSeederProvider.get(), getAnalyticsUseCaseProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<UserStatsDao> userStatsDaoProvider,
      Provider<LearningLogDao> learningLogDaoProvider,
      Provider<DatabaseSeeder> databaseSeederProvider,
      Provider<GetAnalyticsUseCase> getAnalyticsUseCaseProvider) {
    return new DashboardViewModel_Factory(userStatsDaoProvider, learningLogDaoProvider, databaseSeederProvider, getAnalyticsUseCaseProvider);
  }

  public static DashboardViewModel newInstance(UserStatsDao userStatsDao,
      LearningLogDao learningLogDao, DatabaseSeeder databaseSeeder,
      GetAnalyticsUseCase getAnalyticsUseCase) {
    return new DashboardViewModel(userStatsDao, learningLogDao, databaseSeeder, getAnalyticsUseCase);
  }
}
