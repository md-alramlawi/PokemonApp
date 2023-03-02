package com.alramlawi.pokemonapp.data.remote

import com.alramlawi.pokemonapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonListing(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListingResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: String,
    ): PokemonDetailsResponse

    companion object {

        operator fun invoke(): PokemonApi {
            return Retrofit.Builder().baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(PokemonApi::class.java)
        }
    }
}