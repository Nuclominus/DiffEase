package com.nuclominus.diffadapter.helpers.viewholders

import androidx.viewbinding.ViewBinding
import com.nuclominus.diffadapter.base.BaseViewHolder

/**
 * Base loading view holder implementation
 */
class LoadingViewHolder<TView : ViewBinding, TModel>(binding: TView) :
    BaseViewHolder<TModel>(binding)