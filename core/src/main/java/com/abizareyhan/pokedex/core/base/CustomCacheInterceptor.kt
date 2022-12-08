package com.abizareyhan.pokedex.core.base

import android.content.Context
import com.abizareyhan.pokedex.core.extension.isConnected
import okhttp3.Interceptor
import okhttp3.Response

class CustomCacheInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())

        return if (context.isConnected()) {
            originalResponse.newBuilder()
                .header("Cache-Control", "max-age=3600")
                .removeHeader("Pragma")
                .build()
        } else {
            originalResponse.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=${60 * 60 * 24 * 30}")
                .removeHeader("Pragma")
                .build()
        }
    }
}