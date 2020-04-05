package com.lamine.bookstore.bookDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lamine.bookstore.App
import com.lamine.bookstore.Book

class BookDetailViewModel(bookId: Int) : ViewModel() {
    private val bookIdLiveData = MutableLiveData<Int>()

    val book: LiveData<Book> = Transformations.switchMap(bookIdLiveData) {
        id -> App.dataBase.bookDao().getBookById(id)
    }

    init {
        bookIdLiveData.value = bookId
    }
}