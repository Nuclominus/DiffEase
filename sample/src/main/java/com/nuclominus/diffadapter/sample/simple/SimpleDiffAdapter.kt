package com.nuclominus.diffadapter.sample.simple

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nuclominus.diffadapter.base.BaseListAdapter
import com.nuclominus.diffadapter.base.BaseViewHolder
import com.nuclominus.diffadapter.base.DiffResultCallback
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffadapter.sample.extensions.baseDiffCallback
import com.nuclominus.diffadapter.sample.viewHolder.MockViewHolder

class SimpleDiffAdapter(private val callback: ListObserver<MultiMock>) :
    BaseListAdapter<MultiMock, BaseViewHolder<MultiMock>>() {

    override fun inflate(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        viewType: Int
    ): BaseViewHolder<MultiMock> {
        return MockViewHolder(
            binding = ItemMockSimpleBinding.inflate(inflater, viewGroup, false),
            callback = callback
        )
    }

    override fun <TInput : MultiMock> getDiffCallback(
        oldItems: List<MultiMock>,
        newItems: List<TInput>
    ): DiffResultCallback<MultiMock, TInput> = baseDiffCallback(oldItems, newItems)
}