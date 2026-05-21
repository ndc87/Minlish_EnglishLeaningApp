package com.minlish.app.di;

import com.minlish.app.data.local.AppDatabase;
import com.minlish.app.data.local.dao.TopicDao;
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
public final class DatabaseModule_ProvideTopicDaoFactory implements Factory<TopicDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideTopicDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TopicDao get() {
    return provideTopicDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideTopicDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideTopicDaoFactory(dbProvider);
  }

  public static TopicDao provideTopicDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTopicDao(db));
  }
}
