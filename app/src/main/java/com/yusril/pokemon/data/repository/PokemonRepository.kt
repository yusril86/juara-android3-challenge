package com.yusril.pokemon.data.repository

import androidx.paging.PagingData
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemonList(
        offset: Int?,
        limit: Int
    ): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetail(pokemonNumber: Int):Flow<Resource<PokemonDetail>>

   suspend fun getFavoritePokemon():List<FavoritePokemonEntity>

   suspend fun insertFavoritePokemon(pokemon:FavoritePokemonEntity)

   suspend fun deleteFavorite(favoritePokemonEntity: FavoritePokemonEntity)

   suspend fun isPokemonFavorite(pokemonName: String):FavoritePokemonEntity?
}