package se.go.frendly.presentation.adapters.base

import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.RequestManager

abstract class ListObserver<TModel> {

    open val lifecycleOwner: LifecycleOwner?
        get() = null

    open val requestManager: RequestManager?
        get() = null


    open fun onItemClicked(item: TModel){

    }

    open fun onActionClicked(item: TModel){

    }

    open fun onSelectionChanged(itemsCount: Int){

    }
}