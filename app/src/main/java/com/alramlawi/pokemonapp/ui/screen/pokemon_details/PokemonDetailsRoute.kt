package com.alramlawi.pokemonapp.ui.screen.pokemon_details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alramlawi.pokemonapp.data.model.Pokemon
import com.alramlawi.pokemonapp.ui.PokemonRoute


private const val ROUTE = PokemonRoute.PokemonDetails

fun NavController.navigateToPokemonDetails(pokemon: Pokemon) {
    navigate("${ROUTE}?${PokemonDetailsScreenArgs.POKEMON_ID}=${pokemon.id}") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.pokemonDetailsRoute() {
    composable(
        route = "${ROUTE}?${PokemonDetailsScreenArgs.POKEMON_ID}={${PokemonDetailsScreenArgs.POKEMON_ID}}",
        arguments = listOf(
            navArgument(name = PokemonDetailsScreenArgs.POKEMON_ID) { NavType.StringType }
        )
    ) {
        PokemonDetailsScreen()
    }
}

class PokemonDetailsScreenArgs(savedStateHandle: SavedStateHandle) {
    val pokemonId: String? = savedStateHandle[POKEMON_ID]

    companion object {
        const val POKEMON_ID = "pokemon_id"
    }
}
