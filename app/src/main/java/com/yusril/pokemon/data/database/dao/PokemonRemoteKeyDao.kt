package com.yusril.pokemon.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusril.pokemon.data.database.entity.PokemonRemoteKeyEntity

@Dao
interface PokemonRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemonKeys(pokemonRemoteKey: PokemonRemoteKeyEntity)

    @Query("SELECT * FROM remote_keys ORDER BY id DESC")
    suspend fun getPokemonKeys(): List<PokemonRemoteKeyEntity>
}