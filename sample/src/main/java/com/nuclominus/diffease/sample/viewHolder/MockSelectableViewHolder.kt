package com.nuclominus.diffease.sample.viewHolder

import androidx.core.view.isVisible
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.ItemMockSimpleBinding
import com.nuclominus.diffease.selectable.BaseSelectableViewHolder

class MockSelectableViewHolder(
    private val binding: ItemMockSimpleBinding,
    listObserver: ListObserver<MultiMock>,
) : BaseSelectableViewHolder<MultiMock, String>(binding, listObserver) {

    override fun bind(current: MultiMock, isActivated: Boolean) {
        super.bind(current, isActivated)

        with(binding) {
            gSelect.isVisible = isActivated
            root.isActivated = isActivated

            ivAvatar.setImageResource(current.avatar)
            tvName.setText(current.name)
            tvDate.text = current.date
        }
    }

    override fun resolveKey(model: MultiMock): String {
        return model.modelId
    }

}