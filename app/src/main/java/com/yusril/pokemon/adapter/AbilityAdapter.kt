package com.yusril.pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusril.pokemon.data.model.Ability
import com.yusril.pokemon.databinding.ItemAbiltyBinding

class AbilityAdapter : RecyclerView.Adapter<AbilityAdapter.ItemViewHolder>() {
    private val mListAbility : MutableList<Ability> = ArrayList()

    fun updateAdapterAbility(abilityList : List<Ability>){
        mListAbility.clear()
        mListAbility.addAll(abilityList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAbiltyBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            mListAbility[position].apply {
                for (abilityList in mListAbility){
                    tvTypes.text = abilityList.ability?.name
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mListAbility.size
    }

    inner class ItemViewHolder(val binding: ItemAbiltyBinding) :
        RecyclerView.ViewHolder(binding.root)
}