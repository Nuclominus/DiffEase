package com.nuclominus.diffadapter.sample.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view: View, text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, text, length).show()
}