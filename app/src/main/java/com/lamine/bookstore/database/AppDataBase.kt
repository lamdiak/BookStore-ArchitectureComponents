package com.lamine.bookstore.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lamine.bookstore.Book

const val DATABASE_NAME = "Book_Store"

@Database(entities = [Book::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun bookDao(): BookDao
}