package com.nuclominus.diffease.sample.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

internal fun showNotCompletedMessage(view: View) {
    Snackbar.make(view, "Not completed yet", Snackbar.LENGTH_SHORT).show()
}