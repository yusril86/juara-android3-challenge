package com.yusril.trawlbenstest.ui.detail

import androidx.lifecycle.*
import com.yusril.trawlbenstest.data.model.PokemonDetail
import com.yusril.trawlbenstest.data.repository.PokemonRepository
import com.yusril.trawlbenstest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val mPokemonDetail =  MutableLiveData<PokemonDetail?>()

    fun pokemonFetchData(number:Int) : LiveData<Resource<PokemonDetail>> = liveData{
        emit(Resource.Loading())
        emitSource(pokemonRepository.getPokemonDetail(number).asLiveData())
    }
}