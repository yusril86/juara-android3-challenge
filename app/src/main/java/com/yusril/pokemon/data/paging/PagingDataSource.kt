package com.yusril.pokemon.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.network.ApiService

class PagingDataSource(private val apiServices: ApiService): PagingSource<Int, Pokemon>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiServices.getPokemonRemote(position,params.loadSize)
            LoadResult.Page(
                data = responseData.results,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position -1,
                nextKey =  if (responseData.results.isEmpty()) null else position + 1
            )
        }catch (exception : Exception){
            return LoadResult.Error(exception)
        }
    }
}