package com.alramlawi.pokemonapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alramlawi.pokemonapp.data.model.Pokemon
import com.alramlawi.pokemonapp.data.PokemonRepository
import com.alramlawi.pokemonapp.utils.Constants

class PokemonDataSource(
    private val repo: PokemonRepository
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val limit = Constants.PageSize
            val offset = params.key ?: 0
            val response = repo.getPokemonListing(offset, limit)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = if (response.results.size == limit) offset + limit else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}