package com.yusril.pokemon.data.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.yusril.pokemon.data.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons")
    fun getAllPokemon(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemons")
    suspend fun deleteAll()
}