package com.minlish.app.ui.dashboard;

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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<UserStatsDao> userStatsDaoProvider;

  private final Provider<LearningLogDao> learningLogDaoProvider;

  public DashboardViewModel_Factory(Provider<UserStatsDao> userStatsDaoProvider,
      Provider<LearningLogDao> learningLogDaoProvider) {
    this.userStatsDaoProvider = userStatsDaoProvider;
    this.learningLogDaoProvider = learningLogDaoProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(userStatsDaoProvider.get(), learningLogDaoProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<UserStatsDao> userStatsDaoProvider,
      Provider<LearningLogDao> learningLogDaoProvider) {
    return new DashboardViewModel_Factory(userStatsDaoProvider, learningLogDaoProvider);
  }

  public static DashboardViewModel newInstance(UserStatsDao userStatsDao,
      LearningLogDao learningLogDao) {
    return new DashboardViewModel(userStatsDao, learningLogDao);
  }
}
