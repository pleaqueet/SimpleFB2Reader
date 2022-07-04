package com.example.simplereader.presentation.add

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplereader.R
import com.example.simplereader.data.BooksRepository
import com.example.simplereader.data.room.Book
import com.example.simplereader.utils.FB2ParserUtils
import com.example.simplereader.utils.FilesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.xmlpull.v1.XmlPullParserException
import javax.inject.Inject

@HiltViewModel
class AddBooksViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {
    fun insertBookByUri(uri: Uri?, context: Context) {
        try {
            val book = Book(
                filePath = FilesUtils.pathOfUri(uri),
                fileFormat = FilesUtils.fileFormatOfUri(uri),
                title = FB2ParserUtils.bookTitleFromFB2ByUri(uri, context),
                author = FB2ParserUtils.bookAuthorFromFB2ByUri(uri, context),
            )
            viewModelScope.launch {
                booksRepository.insertBook(book)
            }
        } catch (e: XmlPullParserException) {
            Log.e("FB2 Parser", "File is not FB2 format $e")
            Toast.makeText(context, context.getString(R.string.choose_fb2_book), Toast.LENGTH_SHORT)
                .show()
        }
    }
}