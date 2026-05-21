package com.minlish.app.di;

import com.minlish.app.data.local.AppDatabase;
import com.minlish.app.data.local.dao.StudyLogDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideStudyLogDaoFactory implements Factory<StudyLogDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideStudyLogDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public StudyLogDao get() {
    return provideStudyLogDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideStudyLogDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideStudyLogDaoFactory(dbProvider);
  }

  public static StudyLogDao provideStudyLogDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideStudyLogDao(db));
  }
}
