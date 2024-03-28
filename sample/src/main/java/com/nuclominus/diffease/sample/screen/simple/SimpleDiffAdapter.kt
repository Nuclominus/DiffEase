package com.nuclominus.diffease.sample.screen.simple

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nuclominus.diffease.base.DiffEaseAdapter
import com.nuclominus.diffease.base.BaseViewHolder
import com.nuclominus.diffease.base.DiffResultCallback
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffease.sample.extensions.baseDiffCallback
import com.nuclominus.diffease.sample.viewHolder.MockViewHolder

class SimpleDiffAdapter(private val callback: ListObserver<MultiMock>) :
    DiffEaseAdapter<MultiMock, BaseViewHolder<MultiMock>>() {

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