package com.nuclominus.diffease.sample.screen.multiType

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nuclominus.diffease.base.DiffEaseAdapter
import com.nuclominus.diffease.base.BaseViewHolder
import com.nuclominus.diffease.base.DiffResultCallback
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.data.MockModel
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.ItemMockActionBinding
import com.nuclominus.diffease.sample.databinding.ItemMockImageBinding
import com.nuclominus.diffease.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffease.error.UnknownViewTypeException
import com.nuclominus.diffease.sample.extensions.baseDiffCallback
import com.nuclominus.diffease.sample.viewHolder.MockActionViewHolder
import com.nuclominus.diffease.sample.viewHolder.MockImageViewHolder
import com.nuclominus.diffease.sample.viewHolder.MockViewHolder

class SampleMultiTypeAdapter(private val callback: ListObserver<MultiMock>? = null) :
    DiffEaseAdapter<MultiMock, BaseViewHolder<MultiMock>>() {

    override fun getItemViewType(position: Int): Int = items[position].viewType

    override fun inflate(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        viewType: Int
    ): BaseViewHolder<MultiMock> {
        return when (viewType) {
            MockModel.VIEW_TYPE_SIMPLE_CELL ->
                MockViewHolder(
                    binding = ItemMockSimpleBinding.inflate(inflater, viewGroup, false),
                    callback = callback
                )
            MockModel.VIEW_TYPE_ACTION_CELL ->
                MockActionViewHolder(
                    binding = ItemMockActionBinding.inflate(inflater, viewGroup, false),
                    callback = callback
                )
            MockModel.VIEW_TYPE_IMAGE_CELL ->
                MockImageViewHolder(
                    binding = ItemMockImageBinding.inflate(inflater, viewGroup, false),
                    callback = callback
                )
            else -> throw UnknownViewTypeException(viewType)
        }
    }

    override fun <TInput : MultiMock> getDiffCallback(
        oldItems: List<MultiMock>,
        newItems: List<TInput>
    ): DiffResultCallback<MultiMock, TInput> = baseDiffCallback(oldItems, newItems)

}