package com.abizareyhan.pokedex.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abizareyhan.pokedex.data.constant.APIEndpoint
import com.abizareyhan.pokedex.data.service.PokeApiService
import com.abizareyhan.pokedex.domain.entity.PokemonEntity
import com.abizareyhan.pokedex.domain.entity.PokemonTypeEntity

class PokemonDataSource(
    private val pokeApiService: PokeApiService,
    private val queryString: String? = null,
    private val sortAsc: Boolean = true
): PagingSource<Int, PokemonEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        return try {
            val offset = params.key ?: 0
            val limit = if(sortAsc) {
                if(queryString.isNullOrEmpty()) {
                    params.loadSize
                } else {
                    50
                }
            } else {
                Int.MAX_VALUE
            }

            val response = pokeApiService.getPokemonList(
                limit = limit,
                offset = offset
            )

            val finalData = (if(queryString.isNullOrEmpty()) {
                response.results ?: listOf()
            } else {
                response.results?.filter {
                    it.name?.contains(queryString, true) ?: false
                } ?: listOf()
            }).map {
                val pokemonDetail = pokeApiService.getPokemonDetail(it.id)

                PokemonEntity(
                    id = it.id,
                    name = it.name.orEmpty(),
                    thumbnail = "${APIEndpoint.BASE_THUMBNAIL_URL}/${it.id}.png",
                    types = pokemonDetail.types?.map {
                        PokemonTypeEntity(
                            name = it.type?.name.orEmpty()
                        )
                    } ?: listOf(),
                    weight = pokemonDetail.weight?.toLong() ?: 0,
                    height = pokemonDetail.height?.toLong() ?: 0,
                    abilities = pokemonDetail.abilities?.map {
                        it.ability?.name.orEmpty()
                    } ?: listOf(),
                    baseStats = pokemonDetail.stats?.map {
                        Pair(
                            it.stat?.name.orEmpty(),
                            it.baseStat ?: 0
                        )
                    } ?: listOf()
                )
            }



            LoadResult.Page(
                data = if(sortAsc) {
                    finalData
                } else {
                    finalData.asReversed()
                },
                prevKey = if(offset == 0) {
                    null
                } else {
                    offset - limit
                },
                nextKey = if (response.next == null) {
                    null
                } else {
                    offset + limit
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}