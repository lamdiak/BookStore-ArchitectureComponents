package com.lamine.bookstore.repository

import com.lamine.bookstore.Book
import timber.log.Timber

private const val INDEX_OFFSET = 2

private val BOOKS = listOf(
    Book(0, "The lord of the rings", "J.R.R Tolkein",
                "Le Lorem Ipsum est simplement du faux texte employé " +
                        "dans la composition et la mise en page avant impression. " +
                        "Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis " +
                        "les années 1500",
        "https://i.pinimg.com/originals/8f/a7/db/8fa7dbac6ae5e03f4a2b1757e58e09e0.jpg"),
    Book(0, "Harry Potter: vol 1 ", "Pierre Emrick Aubamegnague",
        "On sait depuis longtemps que travailler avec du texte lisible et " +
                "contenant du sens est source de distractions, et empêche de se concentrer " +
                "sur la mise en page elle-même. L'avantage du Lorem Ipsum sur un texte " +
                "générique comme 'Du texte. Du texte. Du texte.' est qu'il possède une " +
                "distribution de lettres plus ou moins normale, et en tout cas comparable " +
                "avec celle du français standard. De nombreuses suites logicielles de mise " +
                "en page ou éditeurs de sites Web ont fait du Lorem Ipsum leur faux texte par " +
                "défaut, et une recherche pour 'Lorem Ipsum' vous conduira vers de nombreux sites " +
                "qui n'en sont encore qu'à leur phase de construction.",
        "https://www.ambient-it.net/wp-content/uploads/2016/12/android_studio-logo-175.png")
)


class FakeBookApi {

    companion object{
        private var bookIndex = 0
    }
    fun loadBooks(): List<Book> {
        val bookSize = BOOKS.size
        var newBookIndex = (bookSize + INDEX_OFFSET) % bookSize

        if (newBookIndex == 0){
            newBookIndex = bookSize
        }
        Timber.i("Book range : $bookIndex / $newBookIndex")
        val bookSlice = BOOKS.subList(bookIndex,newBookIndex)

        bookIndex = newBookIndex % bookSize
        return bookSlice
    }
}