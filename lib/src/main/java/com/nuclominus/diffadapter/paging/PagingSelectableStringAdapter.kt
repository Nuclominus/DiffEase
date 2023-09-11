package com.nuclominus.diffadapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.nuclominus.diffadapter.base.IModel
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.databinding.ItemLoadingLayoutBinding
import com.nuclominus.diffadapter.error.UnknownViewTypeException
import com.nuclominus.diffadapter.ext.mutableCopyOf
import com.nuclominus.diffadapter.helpers.AdapterConstants
import com.nuclominus.diffadapter.helpers.viewholders.LoadingSelectableViewHolder
import com.nuclominus.diffadapter.model.DecoratorsSelectionPredicate
import com.nuclominus.diffadapter.selectable.BaseSelectableListAdapter
import com.nuclominus.diffadapter.selectable.BaseSelectableViewHolder
import com.nuclominus.diffadapter.selectable.SelectableAdapter
import com.nuclominus.diffadapter.selectable.SelectableItemDetailsLookup

abstract class PagingSelectableStringAdapter<TModel : IModel, TVHolder : BaseSelectableViewHolder<TModel, String>>(
    listObserver: ListObserver<TModel>,
    keySelector: (TModel) -> String,
) : BaseSelectableListAdapter<TModel, String, TVHolder>(listObserver, keySelector),
    SelectableAdapter<String> {

    override fun createTracker(
        recyclerView: RecyclerView,
        adapterId: String
    ): SelectionTracker<String> {
        return SelectionTracker.Builder(
            "$adapterId.selection",
            recyclerView,
            SelectableKeyProvider(),
            SelectableItemDetailsLookup<String>(recyclerView),
            StorageStrategy.createStringStorage()
        ).withSelectionPredicate(selectionPredicate())
            .build()
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