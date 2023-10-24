package com.yusril.pokemon.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusril.pokemon.data.database.dao.PokemonDao
import com.yusril.pokemon.data.database.dao.PokemonRemoteKeyDao
import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonRemoteKeyEntity

@Database(entities = [PokemonEntity::class, PokemonRemoteKeyEntity::class], version = 1)
abstract class PokemonDb  : RoomDatabase(){
    companion object {
        private const val  DB_NAME = "pokemon-db"

        @Volatile
        private var instance :PokemonDb? = null

        private fun buildDatabase(context: Context) : PokemonDb{
            return Room.databaseBuilder(
                context,
                PokemonDb::class.java, DB_NAME
            ).build()
        }

        fun getInstance(context: Context):PokemonDb{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

    }
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeyDao(): PokemonRemoteKeyDao
}