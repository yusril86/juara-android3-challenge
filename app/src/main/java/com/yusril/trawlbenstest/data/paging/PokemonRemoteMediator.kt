package com.yusril.trawlbenstest.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.yusril.trawlbenstest.data.database.entity.PokemonEntity
import com.yusril.trawlbenstest.data.database.entity.PokemonRemoteKeyEntity
import com.yusril.trawlbenstest.data.datasource.LocalDataSource
import com.yusril.trawlbenstest.data.datasource.RemoteDataSource
import com.yusril.trawlbenstest.data.mapper.toPokemonEntityList
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getPokemonKeys()
                }
            }

            val data = remoteDataSource.getRemotePokemon(
                offset = loadKey?.after?.toIntOrNull(),
                limit = when (loadType) {
                    LoadType.REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            )

            val items = data.results

            val previous = items.minByOrNull { it.number ?: 0 }?.number?.toString()
            val next = items.maxByOrNull { it.number ?: 0 }?.number?.toString()


            if (previous != null && next != null) {
                localDataSource.savePokemonKeysLocal(
                    PokemonRemoteKeyEntity(
                        0,
                        next,
                        previous
                    )
                )
                items.forEach { item -> item.id = item.number!! }
                items.let {
                    localDataSource.insertPokemonLocal(it.toPokemonEntityList())
                }
            }

            return MediatorResult.Success(endOfPaginationReached = data.next == null)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getPokemonKeys(): PokemonRemoteKeyEntity? =
        localDataSource.getPokemonKeysLocal().firstOrNull()
}


