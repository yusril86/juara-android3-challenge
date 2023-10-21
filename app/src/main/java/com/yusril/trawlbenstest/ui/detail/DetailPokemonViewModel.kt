package com.yusril.trawlbenstest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusril.trawlbenstest.data.model.PokemonDetail
import com.yusril.trawlbenstest.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val mPokemonDetail =  MutableLiveData<PokemonDetail?>()

    fun fetchDataDetail(number:Int){
        viewModelScope.launch {
            try {
                mPokemonDetail.postValue(pokemonRepository.getPokemonDetail(number))
            }catch (exception: Exception){
                mPokemonDetail.postValue(null)
            }
        }
    }

    fun getPokemon(): LiveData<PokemonDetail?> {
        return mPokemonDetail
    }
}