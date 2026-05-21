package com.minlish.app.data.local;

import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.data.local.dao.LearningLogDao;
import com.minlish.app.data.local.dao.ReviewDao;
import com.minlish.app.data.local.dao.UserStatsDao;
import com.minlish.app.data.local.storage.EncryptedAuthStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseSeeder_Factory implements Factory<DatabaseSeeder> {
  private final Provider<CardDao> cardDaoProvider;

  private final Provider<ReviewDao> reviewDaoProvider;

  private final Provider<LearningLogDao> learningLogDaoProvider;

  private final Provider<UserStatsDao> userStatsDaoProvider;

  private final Provider<EncryptedAuthStorage> authStorageProvider;

  private final Provider<CsvWordProvider> csvWordProvider;

  public DatabaseSeeder_Factory(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider, Provider<LearningLogDao> learningLogDaoProvider,
      Provider<UserStatsDao> userStatsDaoProvider,
      Provider<EncryptedAuthStorage> authStorageProvider,
      Provider<CsvWordProvider> csvWordProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.reviewDaoProvider = reviewDaoProvider;
    this.learningLogDaoProvider = learningLogDaoProvider;
    this.userStatsDaoProvider = userStatsDaoProvider;
    this.authStorageProvider = authStorageProvider;
    this.csvWordProvider = csvWordProvider;
  }

  @Override
  public DatabaseSeeder get() {
    return newInstance(cardDaoProvider.get(), reviewDaoProvider.get(), learningLogDaoProvider.get(), userStatsDaoProvider.get(), authStorageProvider.get(), csvWordProvider.get());
  }

  public static DatabaseSeeder_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider, Provider<LearningLogDao> learningLogDaoProvider,
      Provider<UserStatsDao> userStatsDaoProvider,
      Provider<EncryptedAuthStorage> authStorageProvider,
      Provider<CsvWordProvider> csvWordProvider) {
    return new DatabaseSeeder_Factory(cardDaoProvider, reviewDaoProvider, learningLogDaoProvider, userStatsDaoProvider, authStorageProvider, csvWordProvider);
  }

  public static DatabaseSeeder newInstance(CardDao cardDao, ReviewDao reviewDao,
      LearningLogDao learningLogDao, UserStatsDao userStatsDao, EncryptedAuthStorage authStorage,
      CsvWordProvider csvWordProvider) {
    return new DatabaseSeeder(cardDao, reviewDao, learningLogDao, userStatsDao, authStorage, csvWordProvider);
  }
}
