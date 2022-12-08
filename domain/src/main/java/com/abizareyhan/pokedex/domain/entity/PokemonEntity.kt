package com.abizareyhan.pokedex.domain.entity

import androidx.annotation.Keep

@Keep
data class PokemonEntity(
    val id: Int = -1,
    val name: String = "",
    val thumbnail: String = "",
    val types: List<PokemonTypeEntity> = listOf(),
    val weight: Long = 0,
    val height: Long = 0,
    val abilities: List<String> = listOf(),
    val baseStats: List<Pair<String, Int>> = listOf(),
    val description: String = "",
    val evolutionChainList: List<EvolutionChainEntity> = listOf()
)