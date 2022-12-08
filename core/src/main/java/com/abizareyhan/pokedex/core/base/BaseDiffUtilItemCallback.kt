package com.abizareyhan.pokedex.core.base

import androidx.recyclerview.widget.DiffUtil
import com.abizareyhan.pokedex.core.interfaces.BaseDiffUtilModel

class BaseDiffUtilItemCallback<M: BaseDiffUtilModel> : DiffUtil.ItemCallback<M>() {
    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(
        oldItem: M,
        newItem: M
    ): Boolean {
        return oldItem.areContentsTheSame(newItem)
    }
}