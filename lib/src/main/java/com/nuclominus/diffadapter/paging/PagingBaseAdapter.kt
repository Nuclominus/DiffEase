package com.nuclominus.diffadapter.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nuclominus.diffadapter.base.BaseListAdapter
import com.nuclominus.diffadapter.base.BaseViewHolder
import com.nuclominus.diffadapter.databinding.ItemLoadingLayoutBinding
import com.nuclominus.diffadapter.error.UnSupportedViewHolderError
import com.nuclominus.diffadapter.helpers.AdapterConstants
import com.nuclominus.diffadapter.helpers.viewholders.LoadingViewHolder
import com.nuclominus.diffadapter.ext.mutableCopyOf

/**
 * Paging adapter
 * Adapter with native implementation of loading decorator
 */
abstract class PagingBaseAdapter<TModel, TVHolder : BaseViewHolder<TModel>> :
    BaseListAdapter<TModel, TVHolder>() {

    @Suppress("UNCHECKED_CAST")
    override fun inflate(inflater: LayoutInflater, viewGroup: ViewGroup?, viewType: Int): TVHolder {
        return when (viewType) {
            AdapterConstants.BASE_VIEW_TYPE_LOADING ->
                ItemLoadingLayoutBinding.inflate(
                    inflater,
                    viewGroup,
                    false
                ).let {
                    LoadingViewHolder<ItemLoadingLayoutBinding, TModel>(it) as TVHolder
                }

            else -> throw UnSupportedViewHolderError(viewType)
        }
    }

    abstract fun withLoading(): TModel?

    fun showLoading() {
        withLoading()?.let { model ->
            val withLoading = items.mutableCopyOf()
                .apply {
                    add(model)
                }
            update(withLoading, true)
        }
    }
}