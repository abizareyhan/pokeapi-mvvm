package com.abizareyhan.pokedex.pokemon.adapter

import android.graphics.Color
import android.view.ViewGroup
import androidx.core.graphics.toColor
import com.abizareyhan.pokedex.core.base.BaseAdapter
import com.abizareyhan.pokedex.core.extension.viewBinding
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemStatsBinding
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemTagsBinding
import com.abizareyhan.pokedex.pokemon.enums.PokemonType
import com.abizareyhan.pokedex.pokemon.model.BaseStat
import com.abizareyhan.pokedex.pokemon.viewholder.PokemonStatsViewHolder
import com.abizareyhan.pokedex.pokemon.viewholder.PokemonTypeViewHolder

class PokemonStatsAdapter(
    var baseColor: Color = Color.BLACK.toColor()
): BaseAdapter<BaseStat, PokemonStatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonStatsViewHolder {
        return PokemonStatsViewHolder(parent.viewBinding(ItemStatsBinding::inflate), baseColor)
    }
}