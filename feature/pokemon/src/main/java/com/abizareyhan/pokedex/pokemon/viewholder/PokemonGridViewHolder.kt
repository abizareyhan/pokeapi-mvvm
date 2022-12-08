package com.abizareyhan.pokedex.pokemon.viewholder

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.abizareyhan.pokedex.core.base.BaseViewHolder
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemPokemonGridBinding
import com.abizareyhan.pokedex.pokemon.enums.PokemonType
import com.abizareyhan.pokedex.pokemon.model.Pokemon
import com.bumptech.glide.Glide

class PokemonGridViewHolder(
    private val itemBinding: ItemPokemonGridBinding,
    private val onPokemonClick: (Pokemon) -> Unit = {}
): BaseViewHolder<Pokemon>(
    itemBinding
) {
    override fun bind(model: Pokemon) {
        with(itemBinding) {
            tvId.text = model.hexID
            tvName.text = model.name.capitalize()

            Glide.with(root.context)
                .load(model.thumbnailURL)
                .centerCrop()
                .into(ivThumbnail)

            val firstPokemonType = model.pokemonTypes.firstOrNull() ?: PokemonType.NORMAL
            val mainColor = ContextCompat.getColor(root.context, firstPokemonType.colorResource)

            root.backgroundTintList = ColorStateList.valueOf(mainColor)
            tvId.setTextColor(mainColor)

            root.setOnClickListener {
                onPokemonClick(model)
            }
        }
    }
}