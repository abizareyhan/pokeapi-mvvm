package com.abizareyhan.pokedex.data.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class PokemonSpeciesResponse(
    @Json(name = "flavor_text_entries") val flavorTextEntries: List<FlavorTextEntries>? = null,
    @Json(name = "evolution_chain") val evolutionChain: NamedAPIResourceResponse? = null
) {
    @Keep
    data class FlavorTextEntries(
        @Json(name = "flavor_text") val flavorText: String? = null,
        @Json(name = "language") val language: NamedAPIResourceResponse? = null
    )
}
