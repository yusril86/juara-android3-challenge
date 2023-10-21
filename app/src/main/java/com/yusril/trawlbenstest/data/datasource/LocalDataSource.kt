package com.yusril.trawlbenstest.data.datasource

import androidx.paging.PagingSource
import com.yusril.trawlbenstest.data.database.dao.PokemonDao
import com.yusril.trawlbenstest.data.database.dao.PokemonRemoteKeyDao
import com.yusril.trawlbenstest.data.database.entity.PokemonEntity
import com.yusril.trawlbenstest.data.database.entity.PokemonRemoteKeyEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val remoteKeyDao: PokemonRemoteKeyDao,
   private val pokemonDao: PokemonDao
) {
    fun getPokemonDataLocal():PagingSource<Int, PokemonEntity>{
        return pokemonDao.getAllPokemon()
    }

    suspend fun insertPokemonLocal(pokemon: List<PokemonEntity>){
        pokemonDao.insertPokemon(pokemon)
    }

    suspend fun deletePokemonLocal(){
        pokemonDao.deleteAll()
    }


    suspend fun getPokemonKeysLocal():List<PokemonRemoteKeyEntity>{
       return remoteKeyDao.getPokemonKeys()
    }

    suspend fun savePokemonKeysLocal(pokemonRemoteKey: PokemonRemoteKeyEntity){
        remoteKeyDao.savePokemonKeys(pokemonRemoteKey)
    }
}