package com.yusril.pokemon.adapter

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yusril.pokemon.R
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.databinding.ItemListPokemonBinding
import com.yusril.pokemon.ui.detail.DetailPokemonActivity
import com.yusril.pokemon.utils.Constant.uriImagePokemon
import com.yusril.pokemon.utils.getDominantColor

class PokemonAdapter : PagingDataAdapter<Pokemon, PokemonAdapter.ItemView>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        val binding =
            ItemListPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemView(binding)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        val data = getItem(position)
        if (data != null){
            holder.binding(data)
        }
    }

    inner class ItemView(private val binding: ItemListPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun binding (data : Pokemon){
                binding.tvNamePokemon.text = data.name
                binding.tvNumberPokemon.text = data.number.toString()


                Glide.with(itemView.context)
                    .load("${uriImagePokemon}/${data.number}.png")
                    .centerCrop()
                    .placeholder(R.drawable.pokeball)
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
//                            val bitmap  = drawable.bitmap
                            getDominantColor(drawable){ colorDominant ->
                                binding.card.setBackgroundColor(colorDominant)
                            }
                            return false
                        }
                    })
                    .into(binding.ivPokemon)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailPokemonActivity::class.java)
                    intent.putExtra("NUMBER_POKEMON",data.number)
                    intent.putExtra("IMAGE_POKEMON","${uriImagePokemon}/${data.number}.png")
                    itemView.context.startActivity(intent)

                }
            }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }


}