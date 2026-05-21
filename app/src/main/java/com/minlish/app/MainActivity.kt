package com.minlish.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.minlish.app.ui.navigation.AppNavigation
import com.minlish.app.ui.theme.MinLishTheme
import dagger.hilt.android.AndroidEntryPoint

import android.Manifest
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.minlish.app.data.worker.StudyReminderWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            scheduleStudyReminder()
        }
    }

    @javax.inject.Inject
    lateinit var databaseSeeder: com.minlish.app.data.local.DatabaseSeeder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        lifecycleScope.launch {
            databaseSeeder.seedIfNecessary()
        }

        checkNotificationPermission()


        setContent {
            MinLishTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            scheduleStudyReminder()
        }
    }

    private fun scheduleStudyReminder() {
        val workRequest = PeriodicWorkRequestBuilder<StudyReminderWorker>(
            24, TimeUnit.HOURS
        ).setConstraints(
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()
        ).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "StudyReminderWork",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}
