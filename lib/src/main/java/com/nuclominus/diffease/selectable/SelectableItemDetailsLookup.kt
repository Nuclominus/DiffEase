package com.nuclominus.diffease.selectable

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class SelectableItemDetailsLookup<TKey> (
        private val rv: RecyclerView) : ItemDetailsLookup<TKey>() {

    @Suppress("UNCHECKED_CAST")
    override fun getItemDetails(e: MotionEvent): ItemDetails<TKey>? {
        val view = rv.findChildViewUnder(e.x, e.y)
        return view?.let {
            (rv.getChildViewHolder(it) as? SelectableViewHolder<TKey>)?.itemDetails()
        }
    }

}