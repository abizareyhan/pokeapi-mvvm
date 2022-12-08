package com.abizareyhan.pokedex.pokemon.adapter

import android.view.ViewGroup
import com.abizareyhan.pokedex.core.base.BaseAdapter
import com.abizareyhan.pokedex.core.extension.viewBinding
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemPokemonGridBinding
import com.abizareyhan.pokedex.pokemon.model.Pokemon
import com.abizareyhan.pokedex.pokemon.viewholder.PokemonGridViewHolder

class PokemonGridAdapter(
    private val onPokemonClick: (Pokemon) -> Unit = {}
): BaseAdapter<Pokemon, PokemonGridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonGridViewHolder {
        return PokemonGridViewHolder(
            parent.viewBinding(ItemPokemonGridBinding::inflate),
            onPokemonClick
        )
    }
}