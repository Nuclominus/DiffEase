package com.nuclominus.diffease.selectable

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.nuclominus.diffease.base.ListObserver

abstract class SelectableLongListAdapter<TModel, TVHolder : BaseSelectableViewHolder<TModel, Long>>(
    listObserver: ListObserver<TModel>,
    keySelector: (TModel) -> Long,
) : DiffEaseSelectableAdapter<TModel, Long, TVHolder>(listObserver, keySelector),
    SelectableAdapter<Long> {

    override fun createTrackerBuilder(
        recyclerView: RecyclerView,
        adapterId: String
    ): SelectionTracker.Builder<Long> {
        return SelectionTracker.Builder(
            "$adapterId.selection",
            recyclerView,
            SelectableKeyProvider(),
            SelectableItemDetailsLookup<Long>(recyclerView),
            StorageStrategy.createLongStorage()
        )
    }
}