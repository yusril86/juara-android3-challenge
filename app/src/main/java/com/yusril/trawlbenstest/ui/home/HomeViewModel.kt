package com.yusril.trawlbenstest.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yusril.trawlbenstest.data.model.Pokemon
import com.yusril.trawlbenstest.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemon() : Flow<PagingData<Pokemon>> =
        pokemonRepository.getPokemonList(limit = 10, offset = null).cachedIn(viewModelScope)

    val surahFetchData: LiveData<PagingData<Pokemon>> = liveData {

        // Ambil data dari Repository
        emitSource( pokemonRepository.getPokemonList(limit = 10, offset = null).cachedIn(viewModelScope).asLiveData())
    }
}