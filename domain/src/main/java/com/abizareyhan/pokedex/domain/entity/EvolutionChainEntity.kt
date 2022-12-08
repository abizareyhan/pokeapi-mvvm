package com.abizareyhan.pokedex.domain.entity

import androidx.annotation.Keep

@Keep
data class EvolutionChainEntity(
    val pokemonEntity: PokemonEntity = PokemonEntity(),
)