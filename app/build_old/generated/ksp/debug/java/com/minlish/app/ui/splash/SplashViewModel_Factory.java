package com.minlish.app.ui.splash;

import com.minlish.app.data.local.DatabaseSeeder;
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
public final class SplashViewModel_Factory implements Factory<SplashViewModel> {
  private final Provider<DatabaseSeeder> databaseSeederProvider;

  public SplashViewModel_Factory(Provider<DatabaseSeeder> databaseSeederProvider) {
    this.databaseSeederProvider = databaseSeederProvider;
  }

  @Override
  public SplashViewModel get() {
    return newInstance(databaseSeederProvider.get());
  }

  public static SplashViewModel_Factory create(Provider<DatabaseSeeder> databaseSeederProvider) {
    return new SplashViewModel_Factory(databaseSeederProvider);
  }

  public static SplashViewModel newInstance(DatabaseSeeder databaseSeeder) {
    return new SplashViewModel(databaseSeeder);
  }
}
