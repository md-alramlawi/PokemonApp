package com.alramlawi.pokemonapp.di

import com.alramlawi.pokemonapp.data.remote.PokemonApi
import com.alramlawi.pokemonapp.data.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providePokemonApi(): PokemonApi = PokemonApi.invoke()

    @Provides
    fun providePokemonRepository(api: PokemonApi): PokemonRepository = PokemonRepository(api)
}