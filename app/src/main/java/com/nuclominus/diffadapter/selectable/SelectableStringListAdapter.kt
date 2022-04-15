package com.nuclominus.diffadapter.selectable

import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import se.go.frendly.presentation.adapters.base.ListObserver

abstract class SelectableStringListAdapter<TModel, TVHolder : BaseSelectableViewHolder<TModel, String>>(
    listObserver: ListObserver<TModel>,
    keySelector: (TModel) -> String,
) : BaseSelectableListAdapter<TModel, String, TVHolder>(listObserver, keySelector),
    SelectableAdapter<String> {

    override fun createTracker(recyclerView: RecyclerView, adapterId: String): SelectionTracker<String> {
        return SelectionTracker.Builder(
            "$adapterId.selection",
            recyclerView,
            SelectableKeyProvider(),
            SelectableItemDetailsLookup<String>(recyclerView),
            StorageStrategy.createStringStorage()
        ).withSelectionPredicate(selectionPredicate())
            .build()
    }
}