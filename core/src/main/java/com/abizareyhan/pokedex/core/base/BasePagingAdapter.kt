package com.abizareyhan.pokedex.core.base

import androidx.paging.PagingDataAdapter
import com.abizareyhan.pokedex.core.interfaces.BaseDiffUtilModel

abstract class BasePagingAdapter<M: BaseDiffUtilModel, V: BaseViewHolder<M>>: PagingDataAdapter<M, V>(
    BaseDiffUtilItemCallback()
) {
    protected fun getItemAtPosition(position: Int): M? {
        return getItem(position)
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        getItemAtPosition(position)?.let {
            holder.bind(it)
        }
    }
}