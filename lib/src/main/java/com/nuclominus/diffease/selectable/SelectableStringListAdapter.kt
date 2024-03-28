package com.nuclominus.diffease.selectable

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.nuclominus.diffease.base.ListObserver

abstract class SelectableStringListAdapter<TModel, TVHolder : BaseSelectableViewHolder<TModel, String>>(
    listObserver: ListObserver<TModel>,
    keySelector: (TModel) -> String,
) : DiffEaseSelectableAdapter<TModel, String, TVHolder>(listObserver, keySelector),
    SelectableAdapter<String> {

    override fun createTrackerBuilder(
        recyclerView: RecyclerView,
        adapterId: String
    ): SelectionTracker.Builder<String> {
        return SelectionTracker.Builder(
            "$adapterId.selection",
            recyclerView,
            SelectableKeyProvider(),
            SelectableItemDetailsLookup<String>(recyclerView),
            StorageStrategy.createStringStorage()
        )
    }
}