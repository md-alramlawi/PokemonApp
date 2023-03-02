package com.alramlawi.pokemonapp.data.mapper

import com.alramlawi.pokemonapp.data.remote.PokemonDetailsResponse
import com.alramlawi.pokemonapp.data.model.PokemonData

fun PokemonDetailsResponse.asPokemonData(): PokemonData = PokemonData(
    name = this.name,
    imageUrl = this.sprites.other.home.frontDefault,
    types = this.types.map { it.type.name },
    weight = this.weight,
    height = this.height
)