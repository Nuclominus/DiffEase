package com.nuclominus.diffadapter.helpers.viewholders

import androidx.viewbinding.ViewBinding
import com.nuclominus.diffadapter.base.BaseViewHolder
import com.nuclominus.diffadapter.base.IModel
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.helpers.AdapterConstants
import com.nuclominus.diffadapter.selectable.BaseSelectableViewHolder

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