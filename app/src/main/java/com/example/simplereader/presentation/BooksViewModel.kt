package com.example.simplereader.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.simplereader.data.BooksRepository
import com.example.simplereader.data.room.Book
import com.example.simplereader.utils.FB2ParserUtils
import com.example.simplereader.utils.FilesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {
    val allBooks = booksRepository.allBooks.asLiveData()

    fun insertBook(book: Book) {
        viewModelScope.launch {
            booksRepository.insertBook(book)
        }
    }
}