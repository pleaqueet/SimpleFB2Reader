package com.example.simplereader.core

import android.content.Context
import android.net.Uri
import com.example.simplereader.data.model.Book
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringReader
import javax.inject.Inject

class FB2BookParser @Inject constructor(
    private val context: Context,
    private val fileFormatter: FileFormatter
) {
    fun parseBookByUri(uri: Uri): Book {
        return Book(
            title = bookTitleFromFB2ByUri(uri),
            author = bookAuthorFromFB2ByUri(uri),
            body = bookBodyFromFB2ByUri(uri),
            description = bookDescriptionFromFB2ByUri(uri),
            fileFormat = fileFormatter.fileFormatOfUri(uri),
            filePath = fileFormatter.pathOfUri(uri)
        )
    }

    private fun bookBodyFromFB2ByUri(uri: Uri?) =
        rawContentByXmlTag("body", uri)

    private fun bookDescriptionFromFB2ByUri(uri: Uri?) =
        rawContentByXmlTag("description", uri)

    private fun bookTitleFromFB2ByUri(uri: Uri?) =
        firstTextInFileByTag(uri = uri, tag = "book-title")

    private fun bookAuthorFromFB2ByUri(uri: Uri?) =
        firstTextInFileByTag(
            uri = uri,
            tag = "first-name"
        ) + " " + firstTextInFileByTag(
            uri = uri,
            tag = "middle-name"
        ) + " " + firstTextInFileByTag(
            uri = uri,
            tag = "last-name"
        )

    private fun firstTextInFileByTag(uri: Uri?, tag: String): String {
        val factory = XmlPullParserFactory.newInstance()
        val inputStream = context.contentResolver.openInputStream(uri!!)
        val xpp = factory.newPullParser()
        factory.isNamespaceAware = true
        xpp.setInput(StringReader(inputStreamWithContentToString(inputStream!!)))
        var eventType = xpp.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG && xpp.name == tag) {
                xpp.next()
                return xpp.text
            }
            eventType = xpp.next()
        }
        return ""
    }

    private fun inputStreamWithContentToString(inputStream: InputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            total.append(line).append('\n')
        }
        return total.toString()
    }

    private fun rawContentByXmlTag(tag: String, uri: Uri?): String {
        val inputStream = context.contentResolver.openInputStream(uri!!)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val total = StringBuilder()
        var line: String?

        var flag = false
        while (bufferedReader.readLine().also { line = it } != null) {
            println(line)
            if (flag) {
                total.append(line).append('\n')
            }
            if (line?.contains("<$tag>") == true) {
                flag = true
            } else if (line?.contains("</$tag>") == true) {
                break
            }
        }
        return total.toString()
    }
}