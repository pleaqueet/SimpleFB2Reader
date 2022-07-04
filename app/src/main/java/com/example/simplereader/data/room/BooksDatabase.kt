package com.example.simplereader.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class],
    version = 1
)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao
}