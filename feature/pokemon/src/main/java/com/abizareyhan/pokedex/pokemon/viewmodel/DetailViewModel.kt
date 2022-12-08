package com.abizareyhan.pokedex.pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.abizareyhan.pokedex.domain.base.Resource
import com.abizareyhan.pokedex.domain.base.ResourceState
import com.abizareyhan.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.abizareyhan.pokedex.pokemon.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
): ViewModel() {
    val getPokemonDetailLiveData: LiveData<Resource<Pokemon>> = Transformations.switchMap(
        getPokemonDetailUseCase.getResultLiveData()
    ) {
        val liveData: MutableLiveData<Resource<Pokemon>> = MutableLiveData()

        when(it.status) {
            ResourceState.SUCCESS -> {
                val pokemon = Pokemon(it.data)

                liveData.postValue(
                    Resource.success(pokemon)
                )
            }
            ResourceState.FAILED -> liveData.postValue(it.convertError())
        }

        liveData
    }

    fun getPokemonDetail(id: Int) {
        getPokemonDetailUseCase(id)
    }
}