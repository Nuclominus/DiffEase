package com.nuclominus.diffease.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.nuclominus.diffease.base.IModel
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.databinding.ItemLoadingLayoutBinding
import com.nuclominus.diffease.error.UnknownViewTypeException
import com.nuclominus.diffease.ext.mutableCopyOf
import com.nuclominus.diffease.helpers.AdapterConstants
import com.nuclominus.diffease.helpers.viewholders.LoadingSelectableViewHolder
import com.nuclominus.diffease.model.DecoratorsSelectionPredicate
import com.nuclominus.diffease.selectable.DiffEaseSelectableAdapter
import com.nuclominus.diffease.selectable.BaseSelectableViewHolder
import com.nuclominus.diffease.selectable.SelectableAdapter
import com.nuclominus.diffease.selectable.SelectableItemDetailsLookup

abstract class PagingSelectableStringAdapter<TModel : IModel, TVHolder : BaseSelectableViewHolder<TModel, String>>(
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

    override fun selectionPredicate(): SelectionTracker.SelectionPredicate<String> {
        return DecoratorsSelectionPredicate()
    }

    @Suppress("UNCHECKED_CAST")
    override fun inflate(inflater: LayoutInflater, viewGroup: ViewGroup?, viewType: Int): TVHolder {
        return when (viewType) {
            AdapterConstants.BaseView.BASE_VIEW_TYPE_LOADING ->
                ItemLoadingLayoutBinding.inflate(
                    inflater,
                    viewGroup,
                    false
                ).let {
                    LoadingSelectableViewHolder<ItemLoadingLayoutBinding, TModel>(it) as TVHolder
                }

            else -> throw UnknownViewTypeException(viewType)
        }
    }

    protected abstract fun withLoading(): TModel?

    fun showLoading() {
        // skip adding loading element if already present end of list
        items.find { it.viewType == AdapterConstants.BaseView.BASE_VIEW_TYPE_END }
            ?.let { return }

        withLoading()?.let { model ->
            val withLoading = items.mutableCopyOf()
                .apply {
                    add(model)
                }
            update(withLoading, true)
        }
    }
}