package com.yusril.pokemon.adapter

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yusril.pokemon.R
import com.yusril.pokemon.data.database.entity.FavoritePokemonEntity
import com.yusril.pokemon.databinding.ItemListPokemonBinding
import com.yusril.pokemon.utils.Constant
import com.yusril.pokemon.utils.getDominantColor

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ItemView>() {
    private val mListFavoritePokemon: MutableList<FavoritePokemonEntity?> = ArrayList()


    fun updateData(listFavorite : List<FavoritePokemonEntity>){
        mListFavoritePokemon.addAll(listFavorite)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            ItemListPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        val data = mListFavoritePokemon[position]
        if (data != null){
            holder.bind(data)
        }
    }

    override fun getItemCount(): Int {
        return mListFavoritePokemon.size
    }

    inner class ItemView(val binding: ItemListPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind( data : FavoritePokemonEntity){
                binding.tvNamePokemon.text = data.name
                binding.tvNumberPokemon.text = data.id.toString()

                Glide.with(itemView.context)
                    .load("${Constant.uriImagePokemon}/${data.id}.png")
                    .centerCrop()
                    .placeholder(R.drawable.pokeball)
                    .listener(object : RequestListener<Drawable> {
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
            }
    }

}