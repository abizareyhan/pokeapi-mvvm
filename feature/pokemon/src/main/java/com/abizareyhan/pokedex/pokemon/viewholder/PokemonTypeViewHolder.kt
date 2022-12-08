package com.abizareyhan.pokedex.pokemon.viewholder

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.abizareyhan.pokedex.core.base.BaseViewHolder
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemTagsBinding
import com.abizareyhan.pokedex.pokemon.enums.PokemonType

class PokemonTypeViewHolder(
    private val itemBinding: ItemTagsBinding,
): BaseViewHolder<PokemonType>(itemBinding) {
    override fun bind(model: PokemonType) {
        with(itemBinding) {
            tvTag.text = root.context.getString(model.nameResource)
            root.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(root.context, model.colorResource))
        }
    }

}