package com.alramlawi.pokemonapp.data.remote

import com.alramlawi.pokemonapp.data.model.Pokemon


data class PokemonListingResponse(
    val count: Int,
    val next: String?,
    val previous: String,
    val results: List<Pokemon>
)