package com.yusril.pokemon.data.repository

import androidx.paging.PagingData
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(
        limit: Int,
        offset: Int?
    ): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetail(pokemonNumber: Int):Flow<Resource<PokemonDetail>>

}