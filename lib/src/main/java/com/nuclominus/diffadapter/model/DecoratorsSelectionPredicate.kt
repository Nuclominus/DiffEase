package com.nuclominus.diffadapter.model

import androidx.recyclerview.selection.SelectionTracker
import com.nuclominus.diffadapter.helpers.AdapterConstants

class DecoratorsSelectionPredicate<T : Any> : SelectionTracker.SelectionPredicate<T>() {

    override fun canSetStateForKey(key: T, nextState: Boolean): Boolean {
        return key != AdapterConstants.BaseSelectableKey.BASE_LOADING_ITEM_KEY &&
                key != AdapterConstants.BaseSelectableKey.BASE_END_ITEM_KEY
    }

    override fun canSetStateAtPosition(position: Int, nextState: Boolean) = true

    override fun canSelectMultiple() = true
}