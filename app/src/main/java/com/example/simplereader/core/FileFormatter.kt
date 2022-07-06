package com.example.simplereader.core

import android.net.Uri

class FileFormatter {
    fun pathOfUri(uri: Uri?): String {
        return uri?.path.toString()
    }

    fun fileFormatOfUri(uri: Uri?): String {
        return uri?.path.toString().substring(uri?.path.toString().lastIndexOf(".") + 1)
    }
}