package com.alramlawi.pokemonapp.ui.screen.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.alramlawi.pokemonapp.data.model.Pokemon
import com.alramlawi.pokemonapp.ui.composable.ErrorItem
import com.alramlawi.pokemonapp.ui.composable.LoadingItem
import com.alramlawi.pokemonapp.ui.composable.PokemonItem
import com.alramlawi.pokemonapp.ui.screen.pokemon_details.navigateToPokemonDetails


@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val lazyList = viewModel.pokemonPager.collectAsLazyPagingItems()
    val combinedLoadStates = lazyList.loadState

    PokemonListContent(list = lazyList, combinedLoadStates = combinedLoadStates) {
        navController.navigateToPokemonDetails(it)
    }
}

@Composable
fun PokemonListContent(
    list: LazyPagingItems<Pokemon>,
    combinedLoadStates: CombinedLoadStates,
    onClickItem: (Pokemon) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(list) { item ->
            item?.let {
                PokemonItem(pokemon = it, onClick = onClickItem)
            }
        }

        when (combinedLoadStates.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> {
                item {
                    ErrorItem(message = "Some error occurred")
                }
            }
        }

        when (combinedLoadStates.refresh) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingItem()
                    }
                }
            }
            is LoadState.Error -> {
                item {
                    ErrorItem(message = "Some error occurred")
                }
            }
        }
    }
}




