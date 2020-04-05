package com.lamine.bookstore.booksList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lamine.bookstore.App
import com.lamine.bookstore.Book

class BooksListViewModel : ViewModel() {

    val books: LiveData<List<Book>> = App.dataBase.bookDao().getAllBooks()

    fun refreshBooks() {
        App.repository.SyncBookNow()
    }
}