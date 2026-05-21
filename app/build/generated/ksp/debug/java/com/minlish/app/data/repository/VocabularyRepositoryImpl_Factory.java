package com.minlish.app.data.repository;

import com.minlish.app.data.local.dao.TopicDao;
import com.minlish.app.data.local.dao.WordDao;
import com.minlish.app.data.remote.api.VocabularyApiService;
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
public final class VocabularyRepositoryImpl_Factory implements Factory<VocabularyRepositoryImpl> {
  private final Provider<TopicDao> topicDaoProvider;

  private final Provider<WordDao> wordDaoProvider;

  private final Provider<VocabularyApiService> apiServiceProvider;

  public VocabularyRepositoryImpl_Factory(Provider<TopicDao> topicDaoProvider,
      Provider<WordDao> wordDaoProvider, Provider<VocabularyApiService> apiServiceProvider) {
    this.topicDaoProvider = topicDaoProvider;
    this.wordDaoProvider = wordDaoProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public VocabularyRepositoryImpl get() {
    return newInstance(topicDaoProvider.get(), wordDaoProvider.get(), apiServiceProvider.get());
  }

  public static VocabularyRepositoryImpl_Factory create(Provider<TopicDao> topicDaoProvider,
      Provider<WordDao> wordDaoProvider, Provider<VocabularyApiService> apiServiceProvider) {
    return new VocabularyRepositoryImpl_Factory(topicDaoProvider, wordDaoProvider, apiServiceProvider);
  }

  public static VocabularyRepositoryImpl newInstance(TopicDao topicDao, WordDao wordDao,
      VocabularyApiService apiService) {
    return new VocabularyRepositoryImpl(topicDao, wordDao, apiService);
  }
}
