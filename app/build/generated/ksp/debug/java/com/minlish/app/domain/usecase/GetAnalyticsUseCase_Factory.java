package com.minlish.app.domain.usecase;

import com.minlish.app.data.local.dao.LearningLogDao;
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
public final class GetAnalyticsUseCase_Factory implements Factory<GetAnalyticsUseCase> {
  private final Provider<LearningLogDao> learningLogDaoProvider;

  private final Provider<ReviewDao> reviewDaoProvider;

  public GetAnalyticsUseCase_Factory(Provider<LearningLogDao> learningLogDaoProvider,
      Provider<ReviewDao> reviewDaoProvider) {
    this.learningLogDaoProvider = learningLogDaoProvider;
    this.reviewDaoProvider = reviewDaoProvider;
  }

  @Override
  public GetAnalyticsUseCase get() {
    return newInstance(learningLogDaoProvider.get(), reviewDaoProvider.get());
  }

  public static GetAnalyticsUseCase_Factory create(Provider<LearningLogDao> learningLogDaoProvider,
      Provider<ReviewDao> reviewDaoProvider) {
    return new GetAnalyticsUseCase_Factory(learningLogDaoProvider, reviewDaoProvider);
  }

  public static GetAnalyticsUseCase newInstance(LearningLogDao learningLogDao,
      ReviewDao reviewDao) {
    return new GetAnalyticsUseCase(learningLogDao, reviewDao);
  }
}
