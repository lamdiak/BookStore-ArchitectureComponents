package com.lamine.bookstore.bookDetail

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lamine.bookstore.Book
import com.lamine.bookstore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import timber.log.Timber

class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BOOK_ID = "bookId"
    }

    private lateinit var viewModel: BookDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookId = intent.getIntExtra(EXTRA_BOOK_ID, 1)
        Timber.i("Book id = $bookId")

        val factory = BookDetailViewModelFactory(bookId)
        viewModel = ViewModelProviders.of(this, factory).get(BookDetailViewModel::class.java)

        viewModel.book.observe(this, Observer {
            it -> updateBook(it)
        })
    }

    private fun updateBook(it: Book) {
        Picasso.get()
            .load(it.pictureUrl)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(bookCover)
        bookTitle.text = it.title
        bookAuthor.text = it.author
        bookSummary.text = it.summury
    }
}
