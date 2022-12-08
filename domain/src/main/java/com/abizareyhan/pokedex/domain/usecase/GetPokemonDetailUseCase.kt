package com.abizareyhan.pokedex.domain.usecase

import com.abizareyhan.pokedex.domain.base.BaseUseCase
import com.abizareyhan.pokedex.domain.entity.PokemonEntity
import com.abizareyhan.pokedex.domain.repository.PokeApiRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokeApiRepository
): BaseUseCase<Int, PokemonEntity>() {
    override fun invoke(parameter: Int) {
        launch {
            transformToUseCaseLiveData(
                repository.getPokemonDetail(parameter)
            )
        }
    }
}