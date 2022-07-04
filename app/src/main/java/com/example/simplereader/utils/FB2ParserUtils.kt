package com.example.simplereader.utils

import android.content.Context
import android.net.Uri
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringReader

object FB2ParserUtils {

    fun bookTitleFromFB2ByUri(uri: Uri?, context: Context): String {
        val inputStream = context.contentResolver.openInputStream(uri!!)
        return if (inputStream != null) {
            val xml = inputStreamWithContentToString(inputStream)
            firstTextInXmlByTag(xml = xml, tag = "book-title") + ""
        } else {
            ""
        }

    }

    fun bookAuthorFromFB2ByUri(uri: Uri?, context: Context): String {
        val inputStream = context.contentResolver.openInputStream(uri!!)
        return if (inputStream != null) {
            val xml = inputStreamWithContentToString(inputStream)
            firstTextInXmlByTag(
                xml = xml,
                tag = "first-name"
            ) + " " + firstTextInXmlByTag(
                xml = xml,
                tag = "middle-name"
            ) + " " + firstTextInXmlByTag(
                xml = xml,
                tag = "last-name"
            )
        } else {
            ""
        }
    }

    private fun firstTextInXmlByTag(xml: String, tag: String): String? {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val xpp = factory.newPullParser()
        xpp.setInput(StringReader(xml))
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
}