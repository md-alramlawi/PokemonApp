package com.alramlawi.pokemonapp.ui.screen.pokemon_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alramlawi.pokemonapp.data.PokemonRepository
import com.alramlawi.pokemonapp.data.Result
import com.alramlawi.pokemonapp.data.model.PokemonData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args: PokemonDetailsScreenArgs = PokemonDetailsScreenArgs(savedStateHandle)

    private var _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state


    init {
        args.pokemonId?.let { fetchPokemonDetails(it) }
    }

    private fun fetchPokemonDetails(id: String) = viewModelScope.launch {
        when (val result = pokemonRepository.getPokemonDetails(id)) {
            is Result.Success -> {
                result.data?.let {
                    _state.emit(State.Data(it))
                }
            }

            is Result.Error -> {
                _state.emit(State.Failed(result.ex?.message.toString()))
            }
        }
    }


    sealed class State(val pokemonData: PokemonData? = null, val message: String? = null) {
        object Loading : State()
        class Failed(message: String) : State(message = message)
        class Data(pokemonData: PokemonData) : State(pokemonData = pokemonData)
    }
}