package com.yusril.pokemon.data.database.dao

import androidx.room.*
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonEntity

@Dao
interface PokemonFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoritePokemon(pokemon: FavoritePokemonEntity):Long

    @Query("SELECT * FROM favorite_pokemon ")
    suspend fun getFavoritePokemon() : List<FavoritePokemonEntity>

    @Delete
    suspend fun deleteFavoritePokemon(pokemon: FavoritePokemonEntity)

    @Query("SELECT * FROM favorite_pokemon WHERE name = :name")
     suspend fun getFavoritePokemonById(name: String): FavoritePokemonEntity
}