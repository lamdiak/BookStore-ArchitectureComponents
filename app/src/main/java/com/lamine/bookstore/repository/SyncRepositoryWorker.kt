package com.lamine.bookstore.repository

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.lamine.bookstore.App
import timber.log.Timber

class SyncRepositoryWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        Timber.i("Synchronizing Books .........")
        val bookApi = FakeBookApi()
        val bookDao = App.dataBase.bookDao()
        bookDao.insertBooks(bookApi.loadBooks())
        return Result.success()
    }
}