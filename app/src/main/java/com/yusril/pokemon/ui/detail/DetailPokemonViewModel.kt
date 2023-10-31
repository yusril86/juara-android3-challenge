package com.yusril.pokemon.ui.detail

import androidx.lifecycle.*
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.model.PokemonDetail
import com.yusril.pokemon.data.repository.PokemonRepository
import com.yusril.pokemon.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    val favoritePokemonList = pokemonRepository.getFavoritePokemon()

    // Fungsi untuk menambahkan Pokemon ke daftar favorit
    fun addFavoritePokemon(favoritePokemon: FavoritePokemonEntity) {
//        val favoritePokemon = FavoritePokemon(id, name, imageUrl)
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.insertFavoritePokemon(favoritePokemon)
        }
    }

    fun isFavorite(pokemonName : String):FavoritePokemonEntity{
        return pokemonRepository.isPokemonFavorite(pokemonName)
    }

    fun isFavoriteLiveData() : LiveData<FavoritePokemonEntity>{
        return mFavorite
    }
}