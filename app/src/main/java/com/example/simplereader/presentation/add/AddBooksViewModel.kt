package com.example.simplereader.presentation.add

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplereader.R
import com.example.simplereader.core.FB2BookParser
import com.example.simplereader.data.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.xmlpull.v1.XmlPullParserException
import javax.inject.Inject

@HiltViewModel
class AddBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    private val fB2BookParser: FB2BookParser
) : ViewModel() {
    fun insertBookByUri(uri: Uri?, context: Context) {
        try {
            val book = fB2BookParser.parseBookByUri(uri!!)
            viewModelScope.launch {
                booksRepository.insertBook(book)
            }
            Toast.makeText(context, "Книга добавлена", Toast.LENGTH_SHORT)
                .show()
        } catch (e: XmlPullParserException) {
            Log.e("FB2 Parser", "File is not FB2 format. Error: $e")
            Toast.makeText(context, context.getString(R.string.choose_fb2_book), Toast.LENGTH_SHORT)
                .show()
        }
    }
}