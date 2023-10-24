package com.yusril.pokemon.ui.detail

import androidx.lifecycle.*
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.data.repository.PokemonRepository
import com.yusril.pokemon.utils.Resource
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