package com.nuclominus.diffadapter.helpers.viewholders

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.viewbinding.ViewBinding
import com.nuclominus.diffadapter.R
import com.nuclominus.diffadapter.base.BaseViewHolder
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.helpers.AdapterConstants
import com.nuclominus.diffadapter.selectable.BaseSelectableViewHolder

class EndViewHolder<T>(
    private val binder: ViewBinding,
    @IdRes private val titleId: Int,
    @StringRes private val stringId: Int = R.string.list_end
) : BaseViewHolder<T>(binder) {
    override fun bind(current: T) {
        super.bind(current)

        binder.root.findViewById<AppCompatTextView>(titleId)?.text = context.getString(stringId)
    }
}

class SelectableEndViewHolder<T>(
    private val binder: ViewBinding,
    listObserver: ListObserver<T>,
    @IdRes private val titleId: Int,
    @StringRes private val stringId: Int = R.string.list_end
) : BaseSelectableViewHolder<T, String>(binder, listObserver) {
    override fun bind(current: T) {
        super.bind(current)

        binder.root.findViewById<AppCompatTextView>(titleId)?.text = context.getString(stringId)
    }

    override fun resolveKey(model: T): String {
        return AdapterConstants.BaseSelectableKey.BASE_END_ITEM_KEY
    }
}