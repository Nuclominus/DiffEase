package com.nuclominus.diffease.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import androidx.recyclerview.widget.RecyclerView

abstract class DiffEaseAdapter<TModel, TVHolder : BaseViewHolder<TModel>> :
    RecyclerView.Adapter<TVHolder>() {

    protected val items: MutableList<TModel> = arrayListOf()

    var observer: ListObserver<TModel>? = null

    override fun getItemId(position: Int): Long {
        return RecyclerView.NO_ID
    }

    protected abstract fun inflate(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        viewType: Int
    ): TVHolder

    protected open fun <TInput : TModel> getDiffCallback(
        oldItems: List<TModel>,
        newItems: List<TInput>
    ): DiffResultCallback<TModel, TInput> {
        return object : DiffResultCallback<TModel, TInput>(oldItems, newItems) {
            override fun compareItems(oldItem: TModel, newItem: TInput) = false
            override fun compareContent(oldItem: TModel, newItem: TInput) = false
        }
    }

    protected open fun getUpdateCallback(): ListUpdateCallback? = null

    final override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TVHolder {
        return inflate(LayoutInflater.from(viewGroup.context), viewGroup, viewType)
    }

    override fun onBindViewHolder(holder: TVHolder, position: Int, payloads: MutableList<Any>) {
        holder.bind(items[position], payloads)
    }

    override fun onBindViewHolder(holder: TVHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewAttachedToWindow(holder: TVHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: TVHolder) {
        holder.onDetach()
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int = items.size

    fun <TInput : TModel> update(
        source: List<TInput>,
        detectMoves: Boolean
    ) {
        val diffCallback = getDiffCallback(items, source)
        val diffResult = DiffUtil.calculateDiff(diffCallback, detectMoves)
        items.clear()
        items.addAll(source)
        onItemsUpdated(source)
        getUpdateCallback()?.let { diffResult.dispatchUpdatesTo(it) }
            ?: diffResult.dispatchUpdatesTo(this)
    }

    protected open fun <TInput : TModel> onItemsUpdated(source: List<TInput>) {}

}