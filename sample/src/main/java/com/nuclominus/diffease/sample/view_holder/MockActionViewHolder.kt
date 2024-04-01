package com.nuclominus.diffease.sample.view_holder

import com.nuclominus.diffease.base.BaseViewHolder
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.ItemMockActionBinding

class MockActionViewHolder(
    private val binding: ItemMockActionBinding,
    override val callback: ListObserver<MultiMock>?
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
            cbLike.apply {
                isChecked = current.isChecked
                setOnClickListener {
                    callback?.onActionClicked(current)
                }
            }
        }
    }
}
