package com.abizareyhan.pokedex.di

import com.abizareyhan.pokedex.data.repository.PokeApiRepositoryImpl
import com.abizareyhan.pokedex.domain.repository.PokeApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun bindPokeApiRepository(repository: PokeApiRepositoryImpl): PokeApiRepository
}