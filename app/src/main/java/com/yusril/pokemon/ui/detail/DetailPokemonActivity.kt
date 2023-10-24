package com.yusril.pokemon.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.yusril.pokemon.R
import com.yusril.pokemon.adapter.TypePokemonAdapter
import com.yusril.pokemon.databinding.ActivityDetailPokemonBinding
import com.yusril.pokemon.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPokemonBinding
    private val viewModel: DetailPokemonViewModel by viewModels()
    private  var adapterType : TypePokemonAdapter = TypePokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarDetail.titleToolbar.text = "Detail Pokemon"
        binding.toolbarDetail.btnBackToolbar.setOnClickListener {
            onBackPressed()
        }
        val number = intent.getIntExtra("NUMBER_POKEMON", 0)
        val image = intent.getStringExtra("IMAGE_POKEMON")

        getPokemonDetail(number, image.toString())
    }

    private fun getPokemonDetail(number: Int, image: String) {
        viewModel.pokemonFetchData(number)
        viewModel.pokemonFetchData(number).observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.pbPokemonDetail.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    Log.d("pokemonNama", it.data?.name.toString())
                    Log.d("pokemonHeight", it.data?.height.toString())
                    binding.pbPokemonDetail.visibility = View.GONE
                    binding.tvDetailNama.text = it.data?.name
                    binding.tvDetailNomor.text = number.toString()
                    binding.tvDetailHeight.text = it.data?.height.toString()
                    binding.tvDetailWeight.text = it.data?.weight.toString()




                    Glide.with(this)
                        .load(image)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(binding.ivImagePokemonDetail)

                    binding.rvTypePokemonDetail.apply {
                        adapter = adapterType
                        setHasFixedSize(true)
                    }
                    adapterType.updateAdapter(it.data?.types!!)
                }

                is Resource.Error -> {
                    binding.pbPokemonDetail.visibility = View.GONE
                    Toast.makeText(this, "Something Else", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}