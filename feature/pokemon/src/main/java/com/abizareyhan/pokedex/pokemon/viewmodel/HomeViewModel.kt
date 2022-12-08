package com.abizareyhan.pokedex.pokemon.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.abizareyhan.pokedex.domain.repository.PokeApiRepository
import com.abizareyhan.pokedex.pokemon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonApiRepository: PokeApiRepository
): ViewModel() {
    fun getPokemonPaging(search: String, sortAsc: Boolean = true): Flow<PagingData<Pokemon>> {
        return pokemonApiRepository.getPokemonPaging(Pair(search, sortAsc)).map { pagingData ->
            pagingData.map { pokemonEntity ->
                Pokemon(pokemonEntity)
            }
        }.cachedIn(viewModelScope)
    }
}