package com.abizareyhan.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.abizareyhan.pokedex.data.constant.APIEndpoint
import com.abizareyhan.pokedex.data.datasource.PokemonDataSource
import com.abizareyhan.pokedex.data.service.PokeApiService
import com.abizareyhan.pokedex.domain.base.Resource
import com.abizareyhan.pokedex.domain.entity.EvolutionChainEntity
import com.abizareyhan.pokedex.domain.entity.PokemonEntity
import com.abizareyhan.pokedex.domain.entity.PokemonTypeEntity
import com.abizareyhan.pokedex.domain.repository.PokeApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokeApiRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService
): PokeApiRepository {
    override fun getPokemonPaging(search: Pair<String, Boolean>): Flow<PagingData<PokemonEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 15,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PokemonDataSource(pokeApiService, search.first, search.second)
            }
        ).flow
    }

    override suspend fun getPokemonDetail(id: Int): Resource<PokemonEntity> {
        return try {
            val responseDetail = pokeApiService.getPokemonDetail(
                id = id
            )

            val responseSpecies = pokeApiService.getPokemonSpecies(
                id = id
            )

            val responseEvolution = pokeApiService.getEvolutionChain(
                id = responseSpecies.evolutionChain?.id ?: -1
            )

            val evolutionChainList = mutableListOf<EvolutionChainEntity>()

            var currentChainResponse = responseEvolution.chain

            while (currentChainResponse != null) {
                val chainPokemonResponse = pokeApiService.getPokemonDetail(
                    id = currentChainResponse.species?.id ?: 0
                )

                evolutionChainList.add(
                    EvolutionChainEntity(
                        pokemonEntity = PokemonEntity(
                            id = chainPokemonResponse.id ?: -1,
                            name = chainPokemonResponse.name.orEmpty(),
                            thumbnail = "${APIEndpoint.BASE_THUMBNAIL_URL}/${chainPokemonResponse.id}.png",
                            types = chainPokemonResponse.types?.map {
                                PokemonTypeEntity(
                                    name = it.type?.name.orEmpty()
                                )
                            } ?: listOf(),
                        )
                    )
                )

                currentChainResponse = currentChainResponse.evolvesTo?.firstOrNull()
            }

            val pokemonEntity = PokemonEntity(
                id = responseDetail.id ?: -1,
                name = responseDetail.name.orEmpty(),
                thumbnail = "${APIEndpoint.BASE_THUMBNAIL_URL}/${responseDetail.id}.png",
                types = responseDetail.types?.map {
                    PokemonTypeEntity(
                        name = it.type?.name.orEmpty()
                    )
                } ?: listOf(),
                weight = responseDetail.weight?.toLong() ?: 0,
                height = responseDetail.height?.toLong() ?: 0,
                abilities = responseDetail.abilities?.map {
                    it.ability?.name.orEmpty()
                } ?: listOf(),
                baseStats = responseDetail.stats?.map {
                    Pair(
                        it.stat?.name.orEmpty(),
                        it.baseStat ?: 0
                    )
                } ?: listOf(),
                description = responseSpecies.flavorTextEntries?.firstOrNull {
                    it.language?.name?.equals("en", true) ?: false
                }?.flavorText?.replace("\n", " ").orEmpty(),
                evolutionChainList = evolutionChainList
            )

            Resource.success(pokemonEntity)
        } catch (e: Exception) {
            Resource.failed(e)
        }
    }
}