package com.lamine.bookstore.booksList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lamine.bookstore.Book
import com.lamine.bookstore.R
import com.squareup.picasso.Picasso

class BookListAdapter(private val books: List<Book>,
                      private val listener: BooksListAdapterListener?)
    : RecyclerView.Adapter<BookListAdapter.ViewHolder>(), View.OnClickListener {


    interface BooksListAdapterListener {
        fun onBookSelected(books: Book)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.CardView)!!
        val bookCover = itemView.findViewById<ImageView>(R.id.bookCover)!!
        val bookTitle = itemView.findViewById<TextView>(R.id.bookTitle)!!
        val bookAuthor = itemView.findViewById<TextView>(R.id.bookAuthor)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // On crée notre View Holder
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return ViewHolder(viewItem)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        with (holder) {
            cardView.setOnClickListener(this@BookListAdapter)
            cardView.tag = book
            bookTitle.text = book.title
            bookAuthor.text = book.author
            Picasso.get()
                .load(book.pictureUrl)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(bookCover)
        }
    }

    override fun getItemCount() = books.size


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.CardView -> listener?.onBookSelected(v?.tag as Book)
        }
    }
}