package com.abizareyhan.pokedex.domain.repository

import androidx.paging.PagingData
import com.abizareyhan.pokedex.domain.base.Resource
import com.abizareyhan.pokedex.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokeApiRepository {
    fun getPokemonPaging(search: Pair<String, Boolean>): Flow<PagingData<PokemonEntity>>
    suspend fun getPokemonDetail(id: Int): Resource<PokemonEntity>
}