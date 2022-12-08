package com.abizareyhan.pokedex.data.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class PokemonListResponse(
    @Json(name = "count") val count: Int? = null,
    @Json(name = "next") val next: String? = null,
    @Json(name = "previous") val previous: String? = null,
    @Json(name = "results") val results: List<NamedAPIResourceResponse>? = null,
)