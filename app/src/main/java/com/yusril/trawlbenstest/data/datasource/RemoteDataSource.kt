package com.yusril.trawlbenstest.data.datasource

import com.yusril.trawlbenstest.data.model.BaseResponse
import com.yusril.trawlbenstest.data.model.Pokemon
import com.yusril.trawlbenstest.data.model.PokemonDetail
import com.yusril.trawlbenstest.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource  @Inject constructor(
    private val apiServices: ApiService,
){
    suspend fun getRemotePokemon(offset: Int?, limit:Int?) : BaseResponse<Pokemon>{
        return apiServices.getPokemonRemote(offset,limit)
    }

    suspend fun getDetailRemotePokemon(pokemonNumber:Int):PokemonDetail{
        return apiServices.getPokemonDetail(pokemonNumber)
    }


}