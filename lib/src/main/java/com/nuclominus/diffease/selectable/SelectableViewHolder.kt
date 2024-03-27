package com.nuclominus.diffease.selectable

import androidx.recyclerview.selection.ItemDetailsLookup

interface SelectableViewHolder<TKey> {
    fun itemDetails(): ItemDetailsLookup.ItemDetails<TKey>?
}