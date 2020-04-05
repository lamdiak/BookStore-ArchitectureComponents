package com.lamine.bookstore.repository

import androidx.work.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BookRepository  {
    val workManager = WorkManager.getInstance()
    private val constraints = Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build()

    fun SyncBookNow() {
        Timber.i("Synchronizing books now")
        val work = OneTimeWorkRequestBuilder<SyncRepositoryWorker>()
            .setConstraints(constraints)
            .build()
        workManager.beginUniqueWork("syncBookNow",ExistingWorkPolicy.KEEP,work)
            .enqueue()
    }

    fun scheduleBooksSync() {
        Timber.i("Synchronizing books every 12 hours")
        val work = PeriodicWorkRequestBuilder<SyncRepositoryWorker>(12,TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniquePeriodicWork("syncScheduleBooks", ExistingPeriodicWorkPolicy.KEEP, work)

    }
}