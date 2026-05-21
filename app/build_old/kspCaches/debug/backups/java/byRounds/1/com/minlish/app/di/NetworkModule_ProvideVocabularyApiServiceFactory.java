package com.minlish.app.di;

import com.minlish.app.data.remote.api.VocabularyApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideVocabularyApiServiceFactory implements Factory<VocabularyApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideVocabularyApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public VocabularyApiService get() {
    return provideVocabularyApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideVocabularyApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideVocabularyApiServiceFactory(retrofitProvider);
  }

  public static VocabularyApiService provideVocabularyApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideVocabularyApiService(retrofit));
  }
}
