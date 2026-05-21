package com.minlish.app.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.minlish.app.domain.repository.VocabularyRepository;
import dagger.internal.DaggerGenerated;
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
public final class DailyReminderWorker_Factory {
  private final Provider<VocabularyRepository> repositoryProvider;

  public DailyReminderWorker_Factory(Provider<VocabularyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public DailyReminderWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, repositoryProvider.get());
  }

  public static DailyReminderWorker_Factory create(
      Provider<VocabularyRepository> repositoryProvider) {
    return new DailyReminderWorker_Factory(repositoryProvider);
  }

  public static DailyReminderWorker newInstance(Context appContext, WorkerParameters workerParams,
      VocabularyRepository repository) {
    return new DailyReminderWorker(appContext, workerParams, repository);
  }
}
