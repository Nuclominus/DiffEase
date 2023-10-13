package com.nuclominus.diffadapter.sample.screen.selectable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
import com.nuclominus.diffadapter.base.DiffResultCallback
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffadapter.sample.extensions.baseDiffCallback
import com.nuclominus.diffadapter.sample.viewHolder.MockSelectableViewHolder
import com.nuclominus.diffadapter.selectable.SelectableStringListAdapter

class SelectableMockAdapter(
    private val listObserver: ListObserver<MultiMock>,
    keySelector: (MultiMock) -> String = { it.modelId },
) : SelectableStringListAdapter<MultiMock, MockSelectableViewHolder>(
    listObserver,
    keySelector,
) {

    private var selectionPredicates: SelectionPredicate<String> =
        SelectionPredicates.createSelectAnything()

    override fun selectionPredicate(): SelectionPredicate<String> {
        return selectionPredicates
    }

    override fun inflate(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        viewType: Int
    ): MockSelectableViewHolder {
        return ItemMockSimpleBinding.inflate(
            inflater,
            viewGroup,
            false
        ).let { MockSelectableViewHolder(it, listObserver) }
    }

    override fun <TInput : MultiMock> getDiffCallback(
        oldItems: List<MultiMock>,
        newItems: List<TInput>
    ): DiffResultCallback<MultiMock, TInput> = baseDiffCallback(oldItems, newItems)

    fun selectAnyMode() {
        clearSelection()
        selectionPredicates = SelectionPredicates.createSelectAnything()
    }

    fun selectSingleMode() {
        clearSelection()
        selectionPredicates = SelectionPredicates.createSelectSingleAnything()
    }

    fun selectRatioMode() {
        clearSelection()
        selectionPredicates = object : SelectionPredicate<String>() {
            override fun canSetStateForKey(key: String, nextState: Boolean): Boolean {
                return getSelection().firstOrNull()?.let { it != key } ?: true
            }

            override fun canSetStateAtPosition(position: Int, nextState: Boolean) = true
            override fun canSelectMultiple() = false
        }
    }

}