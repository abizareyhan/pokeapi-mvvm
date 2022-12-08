package com.abizareyhan.pokedex.pokemon.adapter

import android.view.ViewGroup
import com.abizareyhan.pokedex.core.base.BasePagingAdapter
import com.abizareyhan.pokedex.core.extension.viewBinding
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemPokemonGridBinding
import com.abizareyhan.pokedex.pokemon.viewholder.PokemonGridViewHolder
import com.abizareyhan.pokedex.pokemon.model.Pokemon

class PokemonGridPagingAdapter(
    private val onPokemonClick: (Pokemon) -> Unit = {}
): BasePagingAdapter<Pokemon, PokemonGridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonGridViewHolder {
        return PokemonGridViewHolder(
            parent.viewBinding(ItemPokemonGridBinding::inflate),
            onPokemonClick
        )
    }
}