package com.nuclominus.diffease.selectable

import android.os.Bundle

interface SelectableAdapter<TKey> {

    fun onSaveInstanceState(outState: Bundle)
    fun onRestoreInstanceState(savedState: Bundle?)

    fun hasSelection() : Boolean
    fun getSelection(): List<TKey>
    fun getSelectionCount(): Int
    fun getAllKeys(): List<TKey>
    fun clearSelection()
}