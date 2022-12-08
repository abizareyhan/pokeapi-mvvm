package com.abizareyhan.pokedex.data.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class PokemonDetailResponse(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "height") val height: Int? = null,
    @Json(name = "weight") val weight: Int? = null,
    @Json(name = "types") val types: List<PokemonTypeResponse>? = null,
    @Json(name = "abilities") val abilities: List<PokemonAbilitiesResponse>? = null,
    @Json(name = "stats") val stats: List<PokemonStatsResponse>? = null,
) {
    @Keep
    data class PokemonTypeResponse(
        @Json(name = "type") val type: NamedAPIResourceResponse? = null,
    )

    @Keep
    data class PokemonAbilitiesResponse(
        @Json(name = "ability") val ability: NamedAPIResourceResponse? = null,
    )

    @Keep
    data class PokemonStatsResponse(
        @Json(name = "base_stat") val baseStat: Int? = null,
        @Json(name = "stat") val stat: NamedAPIResourceResponse? = null,
    )
}
