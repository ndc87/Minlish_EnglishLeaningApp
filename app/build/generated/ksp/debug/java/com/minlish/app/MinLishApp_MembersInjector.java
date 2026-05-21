package com.minlish.app;

import androidx.hilt.work.HiltWorkerFactory;
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
public final class MinLishApp_MembersInjector implements MembersInjector<MinLishApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public MinLishApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<MinLishApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new MinLishApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(MinLishApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.minlish.app.MinLishApp.workerFactory")
  public static void injectWorkerFactory(MinLishApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
