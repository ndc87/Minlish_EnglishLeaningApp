package com.minlish.app.data.local.storage;

import android.content.SharedPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class EncryptedAuthStorage_Factory implements Factory<EncryptedAuthStorage> {
  private final Provider<SharedPreferences> sharedPreferencesProvider;

  public EncryptedAuthStorage_Factory(Provider<SharedPreferences> sharedPreferencesProvider) {
    this.sharedPreferencesProvider = sharedPreferencesProvider;
  }

  @Override
  public EncryptedAuthStorage get() {
    return newInstance(sharedPreferencesProvider.get());
  }

  public static EncryptedAuthStorage_Factory create(
      Provider<SharedPreferences> sharedPreferencesProvider) {
    return new EncryptedAuthStorage_Factory(sharedPreferencesProvider);
  }

  public static EncryptedAuthStorage newInstance(SharedPreferences sharedPreferences) {
    return new EncryptedAuthStorage(sharedPreferences);
  }
}
