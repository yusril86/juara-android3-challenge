package com.yusril.trawlbenstest.data.repository

import androidx.paging.PagingData
import com.yusril.trawlbenstest.data.model.Pokemon
import com.yusril.trawlbenstest.data.model.PokemonDetail
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(
        limit: Int,
        offset: Int?
    ): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetail(pokemonNumber: Int):PokemonDetail

}