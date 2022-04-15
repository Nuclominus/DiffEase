package se.go.frendly.presentation.adapters.base

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<TModel>(
    binding: ViewBinding,
    open val callback: ListObserver<TModel>? = null
) : RecyclerView.ViewHolder(binding.root) {

    var model: TModel? = null

    open fun onAttach() {

    }

    open fun onDetach() {

    }

    open fun bind(current: TModel) {
        model = current
    }

    open fun bind(current: TModel, payloads: MutableList<Any>) {
        model = current
        if (payloads.isEmpty())
            bind(current)
    }

    protected val context: Context
        get() = itemView.context

    protected fun getColor(colorId: Int): Int {
        return ContextCompat.getColor(itemView.context, colorId)
    }

    protected fun getString(textId: Int): String {
        return itemView.context.getString(textId)
    }
}