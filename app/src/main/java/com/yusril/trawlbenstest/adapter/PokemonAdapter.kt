package com.yusril.trawlbenstest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusril.trawlbenstest.R
import com.yusril.trawlbenstest.data.model.Pokemon
import com.yusril.trawlbenstest.databinding.ItemListPokemonBinding

class PokemonAdapter : PagingDataAdapter<Pokemon, PokemonAdapter.ItemView>(DIFF_CALLBACK) {
    private val uriImagePokemon: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon"


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