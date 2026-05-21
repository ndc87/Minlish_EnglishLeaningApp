package com.minlish.app.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.minlish.app.data.local.dao.ReviewDao;
import com.minlish.app.util.NotificationHelper;
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
public final class StudyReminderWorker_Factory {
  private final Provider<ReviewDao> reviewDaoProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  public StudyReminderWorker_Factory(Provider<ReviewDao> reviewDaoProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    this.reviewDaoProvider = reviewDaoProvider;
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public StudyReminderWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, reviewDaoProvider.get(), notificationHelperProvider.get());
  }

  public static StudyReminderWorker_Factory create(Provider<ReviewDao> reviewDaoProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    return new StudyReminderWorker_Factory(reviewDaoProvider, notificationHelperProvider);
  }

  public static StudyReminderWorker newInstance(Context context, WorkerParameters workerParams,
      ReviewDao reviewDao, NotificationHelper notificationHelper) {
    return new StudyReminderWorker(context, workerParams, reviewDao, notificationHelper);
  }
}
