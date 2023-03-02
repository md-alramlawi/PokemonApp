package com.alramlawi.pokemonapp.ui.screen.pokemon_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.alramlawi.pokemonapp.data.model.PokemonData
import com.alramlawi.pokemonapp.ui.composable.ErrorItem
import com.alramlawi.pokemonapp.ui.composable.LoadingItem
import com.alramlawi.pokemonapp.ui.composable.PropertyItem
import com.alramlawi.pokemonapp.ui.composable.TagItem
import com.alramlawi.pokemonapp.ui.theme.PokemonAppTheme
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonDetailsViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()


    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when (state) {
            is PokemonDetailsViewModel.State.Loading -> {
                LoadingItem()
            }

            is PokemonDetailsViewModel.State.Failed -> {
                ErrorItem(message = state.message.toString())
            }

            is PokemonDetailsViewModel.State.Data -> {
                state.pokemonData?.let {
                    PokemonDetailsContent(pokemonData = it)
                }
            }
        }
    }

}


@Composable
private fun PokemonDetailsContent(pokemonData: PokemonData) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonData.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

            PropertyItem(title = "Name :", content = pokemonData.name)

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                PropertyItem(title = "Weight :", content = "${pokemonData.weight} Kg")
                PropertyItem(title = "Height :", content = "${pokemonData.height} Cm")
            }
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                pokemonData.types.forEach { tag ->
                    TagItem(tag = tag)
                }
            }
        }


    }

}


@Preview
@Composable
fun PreviewPokemonDetailsContent() {
    PokemonAppTheme {
        PokemonDetailsContent(
            pokemonData = PokemonData(
                name = "Bulbasaur",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
                weight = 20,
                height = 30,
                types = listOf("Water", "Wind", "Fire", "Stone")
            )
        )
    }
}