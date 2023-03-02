package com.alramlawi.pokemonapp.ui.screen.pokemon_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROUTE = "pokemon_list"

fun NavGraphBuilder.pokemonListRoute(navController: NavController) {
    composable(ROUTE) { PokemonListScreen(navController) }
}
