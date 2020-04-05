package com.lamine.bookstore.booksList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lamine.bookstore.Book
import com.lamine.bookstore.R
import com.lamine.bookstore.bookDetail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity(), BookListAdapter.BooksListAdapterListener {

    private lateinit var viewModel : BooksListViewModel
    private lateinit var booksAdapter : BookListAdapter
    private lateinit var books : MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        books = mutableListOf()
        booksAdapter = BookListAdapter(books,this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = booksAdapter
        }

        swiperefresh.setOnRefreshListener { viewModel.refreshBooks() }

        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)
        viewModel.books.observe(this, Observer { newBooks -> updateBooks(newBooks!!)})
    }

    private fun updateBooks(newBooks: List<Book>) {
        Timber.d("List of new Books $newBooks")
        books.clear()
        books.addAll(newBooks)
        booksAdapter.notifyDataSetChanged()
        swiperefresh.isRefreshing = false
    }

    override fun onBookSelected(books: Book) {
            val intent = Intent(this, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.EXTRA_BOOK_ID, books.id)
            startActivity(intent)
    }
}
