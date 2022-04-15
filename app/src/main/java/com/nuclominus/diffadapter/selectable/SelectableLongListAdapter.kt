package com.nuclominus.diffadapter.selectable

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import se.go.frendly.presentation.adapters.base.ListObserver

abstract class SelectableLongListAdapter<TModel, TVHolder : BaseSelectableViewHolder<TModel, Long>>(
    listObserver: ListObserver<TModel>,
    keySelector: (TModel) -> Long,
) : BaseSelectableListAdapter<TModel, Long, TVHolder>(listObserver, keySelector),
    SelectableAdapter<Long> {

    override fun createTracker(recyclerView: RecyclerView, adapterId: String): SelectionTracker<Long> {
        return SelectionTracker.Builder(
            "$adapterId.selection",
                recyclerView,
                SelectableKeyProvider(),
                SelectableItemDetailsLookup<Long>(recyclerView),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(selectionPredicate())
                .build()
    }
}