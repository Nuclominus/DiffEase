package com.nuclominus.diffadapter.selectable

import android.os.Bundle
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.nuclominus.diffadapter.base.BaseListAdapter
import com.nuclominus.diffadapter.base.ListObserver

abstract class BaseSelectableListAdapter<TModel, TKey : Any, TVHolder : BaseSelectableViewHolder<TModel, TKey>>(
    private val listObserver: ListObserver<TModel>,
    protected val keySelector: (TModel) -> TKey
) : BaseListAdapter<TModel, TVHolder>(), SelectableAdapter<TKey> {

    private val _keyToPosition = linkedMapOf<TKey, Int>()
    private var _tracker: SelectionTracker<TKey>? = null
    private var _pendingSavedState: Bundle? = null
    private var _recyclerView: RecyclerView? = null

    protected abstract fun createTracker(recyclerView: RecyclerView, adapterId: String) : SelectionTracker<TKey>

    final override fun onBindViewHolder(holder: TVHolder, position: Int) {
        val item = items[position]
        holder.bind(item, _tracker?.isSelected(keySelector(item)) == true)
    }

    override fun onBindViewHolder(holder: TVHolder, position: Int, payloads: MutableList<Any>) {
        val item = items[position]
        holder.bind(item, _tracker?.isSelected(keySelector(item)) == true)
    }

    final override fun <TInput : TModel> onItemsUpdated(source: List<TInput>) {
        super.onItemsUpdated(source)
        _keyToPosition.clear()
        source.forEachIndexed { index, item ->
            _keyToPosition[keySelector(item)] = index
        }

        _pendingSavedState?.let {
            _tracker?.apply {
                onRestoreInstanceState(it)
                onSelectionChanged(selection.size())
            }

            _recyclerView?.apply {
                layoutManager?.onRestoreInstanceState(it.getParcelable(TAG + resolveAdapterId()))
            }
        }
        _pendingSavedState = null
    }

    final override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        _recyclerView = recyclerView
        _tracker = createTracker(recyclerView, recyclerView.resolveAdapterId()).apply {
            addObserver(SelectionCallback(this))
        }
    }

    final override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        _recyclerView = null
        _tracker?.clearSelection()
        _tracker = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    private fun RecyclerView.resolveAdapterId(): String {
        return context.resources.getResourceEntryName(id)
    }

    override fun clearSelection() {
        _tracker?.clearSelection()
    }

    protected open fun onSelectionChanged(itemsCount: Int) {
        listObserver.onSelectionChanged(itemsCount)
    }

    override fun onRestoreInstanceState(savedState: Bundle?) {
        _pendingSavedState = savedState
    }

    override fun onSaveInstanceState(outState: Bundle) {
        _tracker?.onSaveInstanceState(outState)
        _recyclerView?.apply {
            outState.putParcelable(TAG + resolveAdapterId(), layoutManager?.onSaveInstanceState())
        }
    }

    override fun hasSelection() = _tracker?.hasSelection() == true

    override fun getSelection(): List<TKey> {
        return _tracker?.selection?.toList() ?: listOf()
    }

    override fun getSelectionCount(): Int {
        return _tracker?.selection?.size() ?: 0
    }

    override fun getAllKeys(): List<TKey> {
        return items.map { keySelector(it) }.toList()
    }

    protected open fun selectionPredicate(): SelectionTracker.SelectionPredicate<TKey> {
        return SelectionPredicates.createSelectAnything()
    }

    protected fun getPositionByKey(key: TKey) : Int {
        return _keyToPosition[key] ?: RecyclerView.NO_POSITION
    }

    companion object {
        const val TAG = "BaseSelectableListAdapter"
    }

    protected inner class SelectableKeyProvider : ItemKeyProvider<TKey>(SCOPE_CACHED) {

        override fun getKey(position: Int): TKey? {
            return items.takeIf { it.size > position }?.let { keySelector(it[position]) }
        }

        override fun getPosition(key: TKey): Int {
            return getPositionByKey(key)
        }
    }

    private inner class SelectionCallback(val tracker: SelectionTracker<TKey>) : SelectionTracker.SelectionObserver<TKey>() {
        override fun onSelectionChanged() {
            onSelectionChanged(tracker.selection.size())
        }
    }
}