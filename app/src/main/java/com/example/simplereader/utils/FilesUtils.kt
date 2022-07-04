package com.example.simplereader.utils

import android.net.Uri

object FilesUtils {
    fun pathOfUri(uri: Uri?): String {
        return uri?.path.toString()
    }

    fun fileFormatOfUri(uri: Uri?): String {
        return uri?.path.toString().substring(uri?.path.toString().lastIndexOf(".") + 1)
    }
}