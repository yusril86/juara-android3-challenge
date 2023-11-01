package com.yusril.pokemon.data.datasource

import androidx.paging.PagingSource
import com.yusril.pokemon.data.database.dao.PokemonDao
import com.yusril.pokemon.data.database.dao.PokemonFavoriteDao
import com.yusril.pokemon.data.database.dao.PokemonRemoteKeyDao
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonRemoteKeyEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val remoteKeyDao: PokemonRemoteKeyDao,
   private val pokemonDao: PokemonDao,
    private val favoriteDao: PokemonFavoriteDao
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

    suspend fun insertFavoritePokemon(favoritePokemonEntity: FavoritePokemonEntity):Long{
        return favoriteDao.insertFavoritePokemon(favoritePokemonEntity)
    }

    suspend fun getFavoritePokemon():List<FavoritePokemonEntity>{
        return favoriteDao.getFavoritePokemon()
    }

    suspend fun deleteFavoritePokemon(pokemon: FavoritePokemonEntity){
        favoriteDao.deleteFavoritePokemon(pokemon)
    }

    suspend fun isPokemonFavorite(pokemonName: String): FavoritePokemonEntity {
        return favoriteDao.getFavoritePokemonById(pokemonName)
    }

    suspend fun getPokemonKeysLocal():List<PokemonRemoteKeyEntity>{
       return remoteKeyDao.getPokemonKeys()
    }

    suspend fun savePokemonKeysLocal(pokemonRemoteKey: PokemonRemoteKeyEntity){
        remoteKeyDao.savePokemonKeys(pokemonRemoteKey)
    }
}