package com.yusril.pokemon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusril.pokemon.data.model.Type
import com.yusril.pokemon.databinding.ItemTypeBinding


class TypePokemonAdapter : RecyclerView.Adapter<TypePokemonAdapter.ItemViewHolder>() {
    private val mListType : MutableList<Type> = ArrayList()

    fun updateAdapter(listPokemon:List<Type>){
        mListType.clear()
        mListType.addAll(listPokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            mListType[position].apply {
                for (listType in mListType){
                    when(listType.type?.name){
                        "flying" -> container.setBackgroundColor(Color.CYAN)
                        "poison" -> container.setBackgroundColor(Color.MAGENTA)
                        "steel" -> container.setBackgroundColor(Color.GRAY)
                        "dragon" -> container.setBackgroundColor(Color.RED)
                        "ground" -> container.setBackgroundColor(Color.GRAY)
                        "electric" -> container.setBackgroundColor(Color.YELLOW)
                    }
                    tvTypes.text = listType.type?.name
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return mListType.size
    }

    inner class ItemViewHolder(val binding: ItemTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}