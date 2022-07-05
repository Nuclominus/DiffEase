package com.nuclominus.diffadapter.sample.extensions

import com.nuclominus.diffadapter.base.DiffResultCallback
import com.nuclominus.diffadapter.sample.data.MultiMock

fun <TInput : MultiMock> baseDiffCallback(
    oldItems: List<MultiMock>,
    newItems: List<TInput>
): DiffResultCallback<MultiMock, TInput> {
    return object : DiffResultCallback<MultiMock, TInput>(oldItems, newItems) {
        override fun compareItems(oldItem: MultiMock, newItem: TInput): Boolean {
            return oldItem.modelId == newItem.modelId
        }

        override fun compareContent(oldItem: MultiMock, newItem: TInput): Boolean {
            return oldItem == newItem
        }
    }
}