package com.yusril.pokemon.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject  constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

     val mFavoriteLiveData = MutableLiveData<List<FavoritePokemonEntity>>()


    fun refreshData() {

        viewModelScope.launch {
            mFavoriteLiveData.value = pokemonRepository.getFavoritePokemon()
        }
    }

    /*fun favoriteLiveData() : LiveData<List<FavoritePokemonEntity>>{
        return  mFavoriteLiveData
    }*/


}