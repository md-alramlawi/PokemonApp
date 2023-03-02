package com.alramlawi.pokemonapp.utils

object Constants {
    const val BaseUrl = "https://pokeapi.co/api/v2/"
    const val PageSize = 20
    fun pokemonIcon(id: String): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/$id.png"
}