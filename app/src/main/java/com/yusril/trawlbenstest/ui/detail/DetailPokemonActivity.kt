package com.yusril.trawlbenstest.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.yusril.trawlbenstest.R
import com.yusril.trawlbenstest.databinding.ActivityDetailPokemonBinding
import com.yusril.trawlbenstest.utils.Constant
import com.yusril.trawlbenstest.utils.Constant.uriImagePokemon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailPokemonBinding
    private val viewModel : DetailPokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarDetail.titleToolbar.text = "Detail Pokemon"
        binding.toolbarDetail.btnBackToolbar.setOnClickListener {
            onBackPressed()
        }
        val number = intent.getIntExtra("NUMBER_POKEMON",0)
        val image = intent.getStringExtra("IMAGE_POKEMON")

        getPokemonDetail(number,image.toString())
    }
    private fun getPokemonDetail(number:Int,image:String) {
        viewModel.fetchDataDetail(number)
        viewModel.getPokemon().observe(this) {
            it.also {
                Log.d("pokemonNama", it?.name.toString())
                Log.d("pokemonHeight", it?.height.toString())
                binding.tvDetailNama.text = it?.name
                binding.tvDetailNomor.text = number.toString()
                binding.tvDetailHeight.text = it?.height.toString()
                binding.tvDetailWeight.text = it?.weight.toString()




                Glide.with(this)
                    .load(image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivImagePokemonDetail)
            }
        }
    }


}