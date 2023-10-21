package com.yusril.trawlbenstest.data.repository

import androidx.paging.*
import com.yusril.trawlbenstest.data.datasource.LocalDataSource
import com.yusril.trawlbenstest.data.datasource.RemoteDataSource
import com.yusril.trawlbenstest.data.mapper.toPokemon
import com.yusril.trawlbenstest.data.model.Pokemon
import com.yusril.trawlbenstest.data.model.PokemonDetail
import com.yusril.trawlbenstest.data.paging.PagingDataSource
import com.yusril.trawlbenstest.data.paging.PokemonRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
)  : PokemonRepository{


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getPokemonList( limit: Int, offset: Int?) = Pager(
        config = PagingConfig(
            pageSize = 5
        ),
        remoteMediator = PokemonRemoteMediator(localDataSource,remoteDataSource),
        pagingSourceFactory = { localDataSource.getPokemonDataLocal()}
    ).flow.map { it.map { item -> item.toPokemon() } }

    override suspend fun getPokemonDetail(pokemonNumber: Int): PokemonDetail {
        return remoteDataSource.getDetailRemotePokemon(pokemonNumber)
    }
}