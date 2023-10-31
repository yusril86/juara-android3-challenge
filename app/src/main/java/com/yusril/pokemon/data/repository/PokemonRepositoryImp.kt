package com.yusril.pokemon.data.repository

import androidx.paging.*
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.datasource.LocalDataSource
import com.yusril.pokemon.data.datasource.RemoteDataSource
import com.yusril.pokemon.data.mapper.toPokemon
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.data.paging.PokemonRemoteMediator
import com.yusril.pokemon.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
)  : PokemonRepository{


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPokemonList(offset: Int?, limit: Int): Flow<PagingData<Pokemon>> = Pager(
        config = PagingConfig(
            pageSize = limit
        ),
        remoteMediator = PokemonRemoteMediator(localDataSource,remoteDataSource),
        pagingSourceFactory = { localDataSource.getPokemonDataLocal()}
    ).flow.map { it.map { item -> item.toPokemon() } }

    override suspend fun getPokemonDetail(pokemonNumber: Int): Flow<Resource<PokemonDetail>> = flow{
        emit(Resource.Loading())
        try {
            emit(Resource.Success(remoteDataSource.getDetailRemotePokemon(pokemonNumber)))
        }catch (exception:Exception){
            emit(Resource.Error(exception.toString()))
        }
    }

    override fun getFavoritePokemon(): List<FavoritePokemonEntity> {
        return localDataSource.getFavoritePokemon()
    }

    override fun insertFavoritePokemon(pokemon: FavoritePokemonEntity) {
        localDataSource.insertFavoritePokemon(pokemon)
    }

    override fun deleteFavorite(favoritePokemonEntity: FavoritePokemonEntity) {
        localDataSource.deleteFavoritePokemon(favoritePokemonEntity)
    }

    override fun isPokemonFavorite(pokemonName: String): FavoritePokemonEntity {
        return localDataSource.isPokemonFavorite(pokemonName)
    }


}