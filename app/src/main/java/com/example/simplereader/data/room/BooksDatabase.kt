package com.example.simplereader.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simplereader.data.model.Book

@Database(
    entities = [Book::class],
    version = 1
)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao
}