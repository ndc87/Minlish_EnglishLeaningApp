package com.minlish.app.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.minlish.app.data.local.dao.ReviewDao
import com.minlish.app.util.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class StudyReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val reviewDao: ReviewDao,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val currentTime = System.currentTimeMillis()
        
        // Query due reviews
        val dueReviews = reviewDao.getDueReviews(currentTime).first()
        
        if (dueReviews.isNotEmpty()) {
            notificationHelper.showStudyReminder(dueReviews.size)
        }

        return Result.success()
    }
}
