package com.yusril.trawlbenstest.network

import com.yusril.trawlbenstest.data.model.BaseResponse
import com.yusril.trawlbenstest.data.model.Pokemon
import com.yusril.trawlbenstest.data.model.PokemonDetail
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