package com.nuclominus.diffease.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nuclominus.diffease.base.DiffEaseAdapter
import com.nuclominus.diffease.base.BaseViewHolder
import com.nuclominus.diffease.base.IModel
import com.nuclominus.diffease.databinding.ItemLoadingLayoutBinding
import com.nuclominus.diffease.error.UnknownViewTypeException
import com.nuclominus.diffease.helpers.AdapterConstants
import com.nuclominus.diffease.helpers.viewholders.LoadingViewHolder
import com.nuclominus.diffease.ext.mutableCopyOf

/**
 * Paging adapter
 * Adapter with native implementation of loading decorator
 */
abstract class PagingBaseAdapter<TModel : IModel, TVHolder : BaseViewHolder<TModel>> :
    DiffEaseAdapter<TModel, TVHolder>() {

    @Suppress("UNCHECKED_CAST")
    override fun inflate(inflater: LayoutInflater, viewGroup: ViewGroup?, viewType: Int): TVHolder {
        return when (viewType) {
            AdapterConstants.BaseView.BASE_VIEW_TYPE_LOADING ->
                ItemLoadingLayoutBinding.inflate(
                    inflater,
                    viewGroup,
                    false
                ).let {
                    LoadingViewHolder<ItemLoadingLayoutBinding, TModel>(it) as TVHolder
                }

            else -> throw UnknownViewTypeException(viewType)
        }
    }

    protected abstract fun withLoading(): TModel?

    fun showLoading() {
        // skip adding loading element if already present end of list
        items.find { it.viewType == AdapterConstants.BaseView.BASE_VIEW_TYPE_END }
            ?.let { return }

        withLoading()?.let { model ->
            val withLoading = items.mutableCopyOf()
                .apply {
                    add(model)
                }
            update(withLoading, true)
        }
    }
}