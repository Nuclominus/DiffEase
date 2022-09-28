package com.nuclominus.diffadapter.sample.multiType

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nuclominus.diffadapter.base.BaseListAdapter
import com.nuclominus.diffadapter.base.BaseViewHolder
import com.nuclominus.diffadapter.base.DiffResultCallback
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.sample.data.MockModel
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.databinding.ItemMockActionBinding
import com.nuclominus.diffadapter.sample.databinding.ItemMockImageBinding
import com.nuclominus.diffadapter.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffadapter.error.UnknownViewTypeException
import com.nuclominus.diffadapter.sample.extensions.baseDiffCallback
import com.nuclominus.diffadapter.sample.viewHolder.MockActionViewHolder
import com.nuclominus.diffadapter.sample.viewHolder.MockImageViewHolder
import com.nuclominus.diffadapter.sample.viewHolder.MockViewHolder

class SampleMultiTypeAdapter(private val callback: ListObserver<MultiMock>) :
    BaseListAdapter<MultiMock, BaseViewHolder<MultiMock>>() {

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