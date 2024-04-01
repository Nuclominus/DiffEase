package com.nuclominus.diffease.sample.view_holder

import com.nuclominus.diffease.base.BaseViewHolder
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.ItemMockSimpleBinding

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