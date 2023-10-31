package com.yusril.pokemon.di

import android.content.Context
import com.yusril.pokemon.data.database.dao.PokemonDao
import com.yusril.pokemon.data.database.dao.PokemonFavoriteDao
import com.yusril.pokemon.data.database.dao.PokemonRemoteKeyDao
import com.yusril.pokemon.data.database.room.PokemonDb
import com.yusril.pokemon.data.datasource.LocalDataSource
import com.yusril.pokemon.data.datasource.RemoteDataSource
import com.yusril.pokemon.data.repository.PokemonRepository
import com.yusril.pokemon.data.repository.PokemonRepositoryImp
import com.yusril.pokemon.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun pokemonDatabase(@ApplicationContext appContext: Context) : PokemonDb =
        PokemonDb.getInstance(appContext)

    @Provides
    fun providePokemonRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ):PokemonRepository{
        return PokemonRepositoryImp(remoteDataSource,localDataSource)
    }

    @Provides
    fun providesPokemonDao(pokemonDb: PokemonDb): PokemonDao {
        return pokemonDb.pokemonDao()
    }

    @Provides
    fun providesRemoteKeyPokemon(pokemonDb: PokemonDb): PokemonRemoteKeyDao {
        return pokemonDb.pokemonRemoteKeyDao()
    }

    @Provides
    fun providesFavoritePokemonDao(pokemonDb: PokemonDb): PokemonFavoriteDao {
        return pokemonDb.pokemonFavoriteDao()
    }
}