package com.yusril.pokemon.ui.detail

import androidx.lifecycle.*
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.data.repository.PokemonRepository
import com.yusril.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
//    private val mPokemonDetail =  MutableLiveData<PokemonDetail?>()

    private val mFavorite = MutableLiveData<FavoritePokemonEntity>()

    fun pokemonFetchData(number:Int) : LiveData<Resource<PokemonDetail>> = liveData{
        emit(Resource.Loading())
        emitSource(pokemonRepository.getPokemonDetail(number).asLiveData())
    }



    fun addFavoritePokemon(favoritePokemon: FavoritePokemonEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.insertFavoritePokemon(favoritePokemon)
        }
    }

    fun deleteFavorite(favoritePokemon: FavoritePokemonEntity){
        viewModelScope.launch {
            pokemonRepository.deleteFavorite(favoritePokemon)
        }
    }

    suspend fun isFavorite(pokemonName: String): FavoritePokemonEntity? {
        return withContext(Dispatchers.Default) {
            pokemonRepository.isPokemonFavorite(pokemonName)
        }
    }

//    fun isFavoriteLiveData() : LiveData<FavoritePokemonEntity>{
//        return mFavorite
//    }
}