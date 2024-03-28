package com.nuclominus.diffease.ext

import androidx.recyclerview.widget.LinearLayoutManager
import com.nuclominus.diffease.base.DiffEaseAdapter
import com.nuclominus.diffease.base.BaseViewHolder

fun <TModel, TVHolder : BaseViewHolder<TModel>> DiffEaseAdapter<TModel, TVHolder>.updateByPayload(
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

fun <TModel, TVHolder : BaseViewHolder<TModel>> DiffEaseAdapter<TModel, TVHolder>.updateByPayload(
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