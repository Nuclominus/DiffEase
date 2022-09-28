package com.nuclominus.diffadapter.ext

import androidx.recyclerview.widget.LinearLayoutManager
import com.nuclominus.diffadapter.base.BaseListAdapter
import com.nuclominus.diffadapter.base.BaseViewHolder

fun <TModel, TVHolder : BaseViewHolder<TModel>> BaseListAdapter<TModel, TVHolder>.updateByPayload(
    updateFrom: Int = 0,
    updateTo: Int,
    payload: Any
) {
    notifyItemRangeChanged(
        updateFrom,
        updateTo,
        payload
    )
}

fun <TModel, TVHolder : BaseViewHolder<TModel>> BaseListAdapter<TModel, TVHolder>.updateByPayload(
    layoutManager: LinearLayoutManager,
    payload: Any
) {
    val updateFrom = layoutManager.findFirstCompletelyVisibleItemPosition()
    val updateTo = layoutManager.findLastCompletelyVisibleItemPosition()
    notifyItemRangeChanged(
        updateFrom,
        updateTo,
        payload
    )
}