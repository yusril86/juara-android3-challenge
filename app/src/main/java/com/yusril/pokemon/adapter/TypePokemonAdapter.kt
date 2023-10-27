package com.yusril.pokemon.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yusril.pokemon.R
import com.yusril.pokemon.data.model.Type
import com.yusril.pokemon.databinding.ItemTypeBinding


class TypePokemonAdapter : RecyclerView.Adapter<TypePokemonAdapter.ItemViewHolder>() {
    private val mListType: MutableList<Type> = ArrayList()

    fun updateAdapter(listPokemon: List<Type>) {
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
                for (listType in mListType) {
                    holder.itemView.apply {
                        when (listType.type?.name) {
                            "flying" -> container.setBackgroundColor(context.resources.getColor(R.color.flying))
                            "poison" -> container.setBackgroundColor(context.resources.getColor(R.color.poison))
                            "steel" -> container.setBackgroundColor(context.resources.getColor(R.color.steel))
                            "dragon" -> container.setBackgroundColor(context.resources.getColor(R.color.dragon))
                            "ground" -> container.setBackgroundColor(context.resources.getColor(R.color.ground))
                            "electric" -> container.setBackgroundColor(context.resources.getColor(R.color.electric))
                            "fairy" -> container.setBackgroundColor(context.resources.getColor(R.color.fairy))
                            "fire" -> container.setBackgroundColor(context.resources.getColor(R.color.fire))
                            "bug" -> container.setBackgroundColor(context.resources.getColor(R.color.bug))
                            "ice" -> container.setBackgroundColor(context.resources.getColor(R.color.ice))
                            "water" -> container.setBackgroundColor(context.resources.getColor(R.color.water))
                            "fighting" -> container.setBackgroundColor(context.resources.getColor(R.color.fighting))
                            "grass" -> container.setBackgroundColor(context.resources.getColor(R.color.grass))
                    }

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