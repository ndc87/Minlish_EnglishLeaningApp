package com.minlish.app.data.local;

import com.minlish.app.data.local.dao.CardDao;
import com.minlish.app.data.local.dao.ReviewDao;
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

  private final Provider<EncryptedAuthStorage> authStorageProvider;

  public DatabaseSeeder_Factory(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider, Provider<EncryptedAuthStorage> authStorageProvider) {
    this.cardDaoProvider = cardDaoProvider;
    this.reviewDaoProvider = reviewDaoProvider;
    this.authStorageProvider = authStorageProvider;
  }

  @Override
  public DatabaseSeeder get() {
    return newInstance(cardDaoProvider.get(), reviewDaoProvider.get(), authStorageProvider.get());
  }

  public static DatabaseSeeder_Factory create(Provider<CardDao> cardDaoProvider,
      Provider<ReviewDao> reviewDaoProvider, Provider<EncryptedAuthStorage> authStorageProvider) {
    return new DatabaseSeeder_Factory(cardDaoProvider, reviewDaoProvider, authStorageProvider);
  }

  public static DatabaseSeeder newInstance(CardDao cardDao, ReviewDao reviewDao,
      EncryptedAuthStorage authStorage) {
    return new DatabaseSeeder(cardDao, reviewDao, authStorage);
  }
}
