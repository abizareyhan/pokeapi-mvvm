package com.abizareyhan.pokedex.pokemon.adapter

import android.view.ViewGroup
import com.abizareyhan.pokedex.core.base.BaseAdapter
import com.abizareyhan.pokedex.core.extension.viewBinding
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemTagsBinding
import com.abizareyhan.pokedex.pokemon.enums.PokemonType
import com.abizareyhan.pokedex.pokemon.viewholder.PokemonTypeViewHolder

class PokemonTypeAdapter: BaseAdapter<PokemonType, PokemonTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeViewHolder {
        return PokemonTypeViewHolder(parent.viewBinding(ItemTagsBinding::inflate))
    }
}