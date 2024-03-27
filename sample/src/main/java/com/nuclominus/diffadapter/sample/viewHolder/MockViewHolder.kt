package com.nuclominus.diffadapter.sample.viewHolder

import com.nuclominus.diffadapter.base.BaseViewHolder
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.databinding.ItemMockSimpleBinding

class MockViewHolder(
    private val binding: ItemMockSimpleBinding,
    override val callback: ListObserver<MultiMock>? = null
) : BaseViewHolder<MultiMock>(binding, callback) {

    override fun bind(current: MultiMock) {
        super.bind(current)

        with(binding) {
            root.setOnClickListener {
                callback?.onItemClicked(current)
            }
            ivAvatar.setImageResource(current.avatar)
            tvName.setText(current.name)
            tvDate.text = current.date
        }
    }
}