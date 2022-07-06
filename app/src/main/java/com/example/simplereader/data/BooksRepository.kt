package com.example.simplereader.data

import com.example.simplereader.data.model.Book
import com.example.simplereader.data.room.BookDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val bookDao: BookDao
) {
    val allBooks = bookDao.getAlphabetizedBooks()

    fun getBookByFilePath(filePath: String) : Flow<Book> {
        return bookDao.getBookByFilePath(filePath)
    }

    suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }
}