package com.nuclominus.diffease.helpers.viewholders

import androidx.viewbinding.ViewBinding
import com.nuclominus.diffease.base.BaseViewHolder
import com.nuclominus.diffease.base.IModel
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.helpers.AdapterConstants
import com.nuclominus.diffease.selectable.BaseSelectableViewHolder

/**
 * Base loading view holder implementation
 */
class LoadingViewHolder<TView : ViewBinding, TModel: IModel>(binding: TView) :
    BaseViewHolder<TModel>(binding)

class LoadingSelectableViewHolder<TView : ViewBinding, TModel : IModel>(binding: TView) :
    BaseSelectableViewHolder<TModel, String>(binding, object : ListObserver<TModel>() {}) {
    override fun resolveKey(model: TModel): String? {
        return AdapterConstants.BaseSelectableKey.BASE_LOADING_ITEM_KEY
    }
}