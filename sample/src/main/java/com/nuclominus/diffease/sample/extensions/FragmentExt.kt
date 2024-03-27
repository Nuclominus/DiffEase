package com.nuclominus.diffease.sample.extensions

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.google.android.material.snackbar.Snackbar

fun showSnackBar(view: View, text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, text, length).show()
}

fun Fragment.addMenuProvider(
    onSelected: (MenuItem) -> Boolean,
    @MenuRes menuRes: Int,
    state: Lifecycle.State = Lifecycle.State.RESUMED
) {
    requireActivity().addMenuProvider(
        object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(menuRes, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return onSelected(menuItem)
            }
        }, viewLifecycleOwner, state
    )
}