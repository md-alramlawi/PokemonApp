package com.alramlawi.pokemonapp.data

import com.alramlawi.pokemonapp.data.remote.PokemonApi
import com.alramlawi.pokemonapp.data.remote.PokemonListingResponse
import com.alramlawi.pokemonapp.data.mapper.asPokemonData
import com.alramlawi.pokemonapp.data.model.PokemonData

class PokemonRepository(
    private val api: PokemonApi
) {
    suspend fun getPokemonListing(offset: Int, limit: Int): PokemonListingResponse {
        return api.getPokemonListing(offset = offset, limit = limit)
    }

    suspend fun getPokemonDetails(pokemonId: String): Result<PokemonData> {
        return try {
            val result = api.getPokemonDetails(pokemonId)
            Result.Success(result.asPokemonData())
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}