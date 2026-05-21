package com.minlish.app.di;

import com.minlish.app.data.local.AppDatabase;
import com.minlish.app.data.local.dao.UserStatsDao;
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
public final class DatabaseModule_ProvideUserStatsDaoFactory implements Factory<UserStatsDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideUserStatsDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public UserStatsDao get() {
    return provideUserStatsDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideUserStatsDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideUserStatsDaoFactory(dbProvider);
  }

  public static UserStatsDao provideUserStatsDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideUserStatsDao(db));
  }
}
