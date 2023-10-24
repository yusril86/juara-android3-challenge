package com.yusril.pokemon.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusril.pokemon.R
import com.yusril.pokemon.data.model.Pokemon
import com.yusril.pokemon.databinding.ItemListPokemonBinding
import com.yusril.pokemon.ui.detail.DetailPokemonActivity
import com.yusril.pokemon.utils.Constant.uriImagePokemon

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

    inner class ItemView(val binding: ItemListPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun binding (data : Pokemon){
                binding.tvName.text = data.name
                Glide.with(itemView.context)
                    .load("${uriImagePokemon}/${data.number}.png")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivFoto)
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