package com.abizareyhan.pokedex.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.abizareyhan.pokedex.core.interfaces.BaseModel

abstract class BaseViewHolder<M: BaseModel>(
    open val binding: ViewBinding
): RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(model: M)
}