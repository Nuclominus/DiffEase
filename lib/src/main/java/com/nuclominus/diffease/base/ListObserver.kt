package com.nuclominus.diffease.base

import androidx.lifecycle.LifecycleOwner

abstract class ListObserver<TModel> {

    open val lifecycleOwner: LifecycleOwner?
        get() = null

    open fun onItemClicked(item: TModel){

    }

    open fun onActionClicked(item: TModel){

    }

    open fun onSelectionChanged(itemsCount: Int){

    }
}