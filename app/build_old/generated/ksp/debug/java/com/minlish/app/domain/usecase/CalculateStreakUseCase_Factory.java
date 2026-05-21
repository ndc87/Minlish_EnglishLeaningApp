package com.minlish.app.domain.usecase;

import com.minlish.app.data.local.dao.StudyLogDao;
import com.minlish.app.data.local.dao.UserDao;
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
  private final Provider<StudyLogDao> studyLogDaoProvider;

  private final Provider<UserDao> userDaoProvider;

  public CalculateStreakUseCase_Factory(Provider<StudyLogDao> studyLogDaoProvider,
      Provider<UserDao> userDaoProvider) {
    this.studyLogDaoProvider = studyLogDaoProvider;
    this.userDaoProvider = userDaoProvider;
  }

  @Override
  public CalculateStreakUseCase get() {
    return newInstance(studyLogDaoProvider.get(), userDaoProvider.get());
  }

  public static CalculateStreakUseCase_Factory create(Provider<StudyLogDao> studyLogDaoProvider,
      Provider<UserDao> userDaoProvider) {
    return new CalculateStreakUseCase_Factory(studyLogDaoProvider, userDaoProvider);
  }

  public static CalculateStreakUseCase newInstance(StudyLogDao studyLogDao, UserDao userDao) {
    return new CalculateStreakUseCase(studyLogDao, userDao);
  }
}
