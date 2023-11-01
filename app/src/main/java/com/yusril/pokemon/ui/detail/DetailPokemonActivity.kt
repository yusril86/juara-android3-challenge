package com.yusril.pokemon.ui.detail

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.yusril.pokemon.R
import com.yusril.pokemon.adapter.AbilityAdapter
import com.yusril.pokemon.adapter.TypePokemonAdapter
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.databinding.ActivityDetailPokemonBinding
import com.yusril.pokemon.utils.Resource
import com.yusril.pokemon.utils.getDominantColor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailPokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPokemonBinding
    private val viewModel: DetailPokemonViewModel by viewModels()
    private  var adapterType : TypePokemonAdapter = TypePokemonAdapter()

    private lateinit var adapterAbility : AbilityAdapter

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.toolbarDetail.titleToolbar.text = "Detail Pokemon"
        binding.btnBackToolbar.setOnClickListener {
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
                    binding.pbDetailPokemon.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    Log.d("pokemonNama", it.data?.name.toString())
                    Log.d("pokemonHeight", it.data?.height.toString())




                    binding.pbDetailPokemon.visibility = View.GONE
                    binding.tvNameDetailPokemon.text = it.data?.name
                    binding.tvHeightDetailPokemon.text = it.data?.height.toString()
                    binding.tvWeightDetailPokemon.text = it.data?.weight.toString()

                    getStatusFavorite(it.data?.name.toString())
                    val favoritePokemonEntity = FavoritePokemonEntity(it.data?.id,
                        it.data?.name.toString(),image)


                    Glide.with(this)
                        .load(image)
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background)
                        .listener(object : RequestListener<Drawable>{
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                val drawable  = resource as BitmapDrawable
                                 getDominantColor(drawable){ colorDominant ->
                                    binding.rootView.setBackgroundColor(colorDominant)
                                     adapterAbility = AbilityAdapter(object : AbilityAdapter.AbilityInterface{
                                         override fun colorDominant(): Int {
                                             return colorDominant
                                         }
                                     })
                                     binding.rvAbilityPokemon.apply {
                                         adapter = adapterAbility
                                         setHasFixedSize(true)
                                     }
                                     it.data?.abilities?.let { ability ->
                                         adapterAbility.updateAdapterAbility(ability) }
                                }

                                return false
                            }
                        })
                        .into(binding.ivDetailPokemon)


                    binding.rvTypePokemon.apply {
                        adapter = adapterType
                        setHasFixedSize(true)
                    }
                    adapterType.updateAdapter(it.data?.types!!)

                    binding.btnFavorite.setOnClickListener {
                        if (isFavorite){
                            deleteFavorite(favoritePokemonEntity)
                            Toast.makeText(this, "Remove Favorite", Toast.LENGTH_SHORT).show()
                        }else{
                            insertFavorite(favoritePokemonEntity)
                            Toast.makeText(this, "Add Favorite", Toast.LENGTH_SHORT).show()
                        }
                    }


                    binding.apply {
                        hpProgress.setOnProgressChangeListener { value ->
                            binding.hpProgress.labelText = value.toInt().toString()}

                        attackProgress.setOnProgressChangeListener { value ->
                            attackProgress.labelText = value.toInt().toString()
                        }

                        speedProgress.setOnProgressChangeListener { value->
                            speedProgress.labelText = value.toInt().toString()
                        }

                        defProgress.setOnProgressChangeListener { value->
                            defProgress.labelText = value.toInt().toString()
                        }

                        spAtkProgress.setOnProgressChangeListener { value->
                            spAtkProgress.labelText = value.toInt().toString()
                        }
                        spDefProgress.setOnProgressChangeListener { value->
                            spDefProgress.labelText = value.toInt().toString()
                         }

                        hpProgress.progress = it.data.stats?.get(0)?.baseStat?.toFloat()!!
                        attackProgress.progress = it.data.stats?.get(1)?.baseStat?.toFloat()!!
                        defProgress.progress = it.data.stats?.get(2)?.baseStat?.toFloat()!!
                        spAtkProgress.progress = it.data.stats?.get(3)?.baseStat?.toFloat()!!
                        spDefProgress.progress = it.data.stats?.get(4)?.baseStat?.toFloat()!!
                        speedProgress.progress = it.data.stats?.get(5)?.baseStat?.toFloat()!!
                    }


                }

                is Resource.Error -> {
                    binding.pbDetailPokemon.visibility = View.GONE
                    Toast.makeText(this, "Something Else", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun getStatusFavorite(name: String){
        viewModel.viewModelScope.launch {
            viewModel.isFavorite(name).let { pokemon ->
                if (pokemon !== null) {
                    setFavorite(true)
                }else{
                    setFavorite(false)
                }
            }
        }

    }

    private fun insertFavorite(pokemonEntity: FavoritePokemonEntity){
        viewModel.addFavoritePokemon(pokemonEntity)
        setFavorite(true)
    }

    private fun deleteFavorite(favoritePokemonEntity: FavoritePokemonEntity){
        viewModel.deleteFavorite(favoritePokemonEntity)
        setFavorite(false)
    }

    private fun setFavorite(favorite : Boolean){
        if(favorite){
            isFavorite = true
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        }else{
            isFavorite = false
            binding.btnFavorite.setImageResource(R.drawable.ic_unfavorite)
        }
    }
}