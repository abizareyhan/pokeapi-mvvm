package com.abizareyhan.pokedex.data.model.response

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class NamedAPIResourceResponse(
    @Json(name = "name") val name: String? = null,
    @Json(name = "url") val url: String? = null,
) {
    val id: Int
        get() {
            val matchedResult = Regex(pattern = """/api/v2/(.)+/(\d+)/""").find(url.orEmpty())

            return matchedResult?.groupValues?.get(2)?.toIntOrNull() ?: -1
        }
}