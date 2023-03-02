package com.alramlawi.pokemonapp.ui.screen.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.alramlawi.pokemonapp.data.paging.PokemonDataSource
import com.alramlawi.pokemonapp.data.PokemonRepository
import com.alramlawi.pokemonapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val pokemonPager = Pager(
        PagingConfig(pageSize = Constants.PageSize)
    ) {
        PokemonDataSource(pokemonRepository)
    }.flow.cachedIn(viewModelScope)

}