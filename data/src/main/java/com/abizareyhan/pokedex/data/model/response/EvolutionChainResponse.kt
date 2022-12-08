package com.abizareyhan.pokedex.data.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class EvolutionChainResponse(
    @Json(name = "chain") val chain: ChainResponse? = null
) {
    @Keep
    data class ChainResponse(
        @Json(name = "evolves_to") val evolvesTo: List<ChainResponse>? = null,
        @Json(name = "species") val species: NamedAPIResourceResponse? = null,
    )
}
