package com.lamine.bookstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title: String,
    var author: String,
    var summury: String,
    @ColumnInfo(name = "picture_url")
    var pictureUrl : String
)