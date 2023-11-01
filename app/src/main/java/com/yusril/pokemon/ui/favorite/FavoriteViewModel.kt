package com.yusril.pokemon.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject  constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val mFavoriteLiveData = MutableLiveData<List<FavoritePokemonEntity>>()

    val favoritePokemonList = viewModelScope.launch {
        mFavoriteLiveData.postValue(pokemonRepository.getFavoritePokemon())
    }

    fun favoriteLiveData() : LiveData<List<FavoritePokemonEntity>>{
        return  mFavoriteLiveData
    }


}