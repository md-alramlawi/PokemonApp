package com.alramlawi.pokemonapp.data.model

data class PokemonData(
    val name: String = "",
    val imageUrl: String = "",
    val types: List<String> = emptyList(),
    val weight: Int = 0,
    val height: Int = 0,
)