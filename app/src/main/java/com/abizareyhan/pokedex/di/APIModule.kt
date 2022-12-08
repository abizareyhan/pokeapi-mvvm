package com.abizareyhan.pokedex.di

import android.content.Context
import com.abizareyhan.pokedex.core.base.CustomCacheInterceptor
import com.abizareyhan.pokedex.data.constant.APIEndpoint
import com.abizareyhan.pokedex.data.service.PokeApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize = 100 * 1024 * 1024

        return OkHttpClient.Builder()
            .addInterceptor(CustomCacheInterceptor(context))
            .cache(
                Cache(context.cacheDir, cacheSize.toLong())
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providePokeApiService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): PokeApiService {
        return Retrofit.Builder()
            .baseUrl(APIEndpoint.POKE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApiService::class.java)
    }
}