package com.nuclominus.diffease.sample.screen.selectable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
import com.nuclominus.diffease.base.DiffResultCallback
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffease.sample.extensions.baseDiffCallback
import com.nuclominus.diffease.sample.viewHolder.MockSelectableViewHolder
import com.nuclominus.diffease.selectable.SelectableStringListAdapter

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
        selectionPredicates = SelectionPredicates.createSelectAnything()
        overrideTracker()
    }

    fun selectSingleMode() {
        selectionPredicates = SelectionPredicates.createSelectSingleAnything()
        overrideTracker()
    }
}