package com.nuclominus.diffease.base

import androidx.recyclerview.widget.DiffUtil

abstract class DiffResultCallback<TModel, TInput> constructor(
    var oldItems: List<TModel>,
    var newItems: List<TInput>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size
    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return compareItems(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return compareContent(oldItems[oldItemPosition], newItems[newItemPosition])
    }

    abstract fun compareItems(oldItem: TModel, newItem: TInput): Boolean

    abstract fun compareContent(oldItem: TModel, newItem: TInput): Boolean
}