package com.lamine.bookstore

import android.app.Application
import androidx.room.Room
import com.lamine.bookstore.database.AppDataBase
import com.lamine.bookstore.database.DATABASE_NAME
import com.lamine.bookstore.repository.BookRepository
import timber.log.Timber

class App: Application() {

    companion object {
        lateinit var dataBase: AppDataBase
        lateinit var repository: BookRepository
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(this, AppDataBase::class.java, DATABASE_NAME)
            .build()

        Timber.plant(Timber.DebugTree())
        Timber.e("Hello ! ")

        repository = BookRepository()
        repository.scheduleBooksSync()
    }
}