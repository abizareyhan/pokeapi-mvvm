package com.abizareyhan.pokedex.data.service

import com.abizareyhan.pokedex.data.constant.APIEndpoint
import com.abizareyhan.pokedex.data.model.response.EvolutionChainResponse
import com.abizareyhan.pokedex.data.model.response.PokemonDetailResponse
import com.abizareyhan.pokedex.data.model.response.PokemonListResponse
import com.abizareyhan.pokedex.data.model.response.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET(APIEndpoint.POKE_API_POKEMON_LIST)
    suspend fun getPokemonList(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
    ): PokemonListResponse

    @GET(APIEndpoint.POKE_API_POKEMON_DETAIL)
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetailResponse

    @GET(APIEndpoint.POKE_API_POKEMON_SPECIES)
    suspend fun getPokemonSpecies(
        @Path("id") id: Int
    ): PokemonSpeciesResponse

    @GET(APIEndpoint.POKE_API_EVOLUTION_CHAIN)
    suspend fun getEvolutionChain(
        @Path("id") id: Int
    ): EvolutionChainResponse
}