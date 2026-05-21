package com.minlish.app.data.repository;

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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<EncryptedAuthStorage> encryptedAuthStorageProvider;

  public AuthRepositoryImpl_Factory(Provider<EncryptedAuthStorage> encryptedAuthStorageProvider) {
    this.encryptedAuthStorageProvider = encryptedAuthStorageProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(encryptedAuthStorageProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(
      Provider<EncryptedAuthStorage> encryptedAuthStorageProvider) {
    return new AuthRepositoryImpl_Factory(encryptedAuthStorageProvider);
  }

  public static AuthRepositoryImpl newInstance(EncryptedAuthStorage encryptedAuthStorage) {
    return new AuthRepositoryImpl(encryptedAuthStorage);
  }
}
