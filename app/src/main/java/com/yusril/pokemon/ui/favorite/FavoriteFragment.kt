package com.yusril.pokemon.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.yusril.pokemon.adapter.FavoriteAdapter
import com.yusril.pokemon.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment(), LifecycleObserver {


    private var _binding: FragmentFavoriteBinding? = null
    private val viewModel : FavoriteViewModel by viewModels()
    private val adapterFavorite : FavoriteAdapter = FavoriteAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        fetchData()
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onResume() {
        super.onResume()
       viewModel.refreshData()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        binding.rvLIstPokemon.apply {
            adapter = adapterFavorite
            setHasFixedSize(true)
        }

        observeLiveData()
//        fetchData()


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun observeLiveData() {

        viewModel.mFavoriteLiveData.observe(viewLifecycleOwner, Observer { pokemons ->
            pokemons?.let {

                adapterFavorite.updateData(pokemons)
            }
        })
    }

}