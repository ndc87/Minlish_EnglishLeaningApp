package com.minlish.app;

import com.minlish.app.data.local.DatabaseSeeder;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<DatabaseSeeder> databaseSeederProvider;

  public MainActivity_MembersInjector(Provider<DatabaseSeeder> databaseSeederProvider) {
    this.databaseSeederProvider = databaseSeederProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<DatabaseSeeder> databaseSeederProvider) {
    return new MainActivity_MembersInjector(databaseSeederProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectDatabaseSeeder(instance, databaseSeederProvider.get());
  }

  @InjectedFieldSignature("com.minlish.app.MainActivity.databaseSeeder")
  public static void injectDatabaseSeeder(MainActivity instance, DatabaseSeeder databaseSeeder) {
    instance.databaseSeeder = databaseSeeder;
  }
}
