package com.alramlawi.pokemonapp.data.model


data class Pokemon(
    val name: String,
    val url: String
) {
    val id: String get() = url.split("/").run { this[size - 2] }
}