package com.minlish.app.di;

import com.minlish.app.data.local.AppDatabase;
import com.minlish.app.data.local.dao.ReviewDao;
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
public final class DatabaseModule_ProvideReviewDaoFactory implements Factory<ReviewDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideReviewDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ReviewDao get() {
    return provideReviewDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideReviewDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideReviewDaoFactory(dbProvider);
  }

  public static ReviewDao provideReviewDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideReviewDao(db));
  }
}
