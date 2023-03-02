package com.alramlawi.pokemonapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alramlawi.pokemonapp.ui.screen.pokemon_details.pokemonDetailsRoute
import com.alramlawi.pokemonapp.ui.screen.pokemon_list.pokemonListRoute

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PokemonRoute.PokemonList,
    ) {
        pokemonListRoute(navController)
        pokemonDetailsRoute()
    }
}

object PokemonRoute {
    const val PokemonList = "pokemon_list"
    const val PokemonDetails = "pokemon_details"
}