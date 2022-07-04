package com.example.simplereader.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

object AndroidUtils {
    fun Fragment.toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }
}