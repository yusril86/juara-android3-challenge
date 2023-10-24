package com.yusril.pokemon.network

import com.yusril.pokemon.data.model.BaseResponse
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.data.model.PokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonRemote(
        @Query("offset") offset : Int? = null,
        @Query("limit") limit : Int? = null
    ):BaseResponse<Pokemon>

    @GET("pokemon/{pokemonNumber}")
    suspend fun getPokemonDetail(
        @Path("pokemonNumber") pokemonNumber: Int,
    ): PokemonDetail
}