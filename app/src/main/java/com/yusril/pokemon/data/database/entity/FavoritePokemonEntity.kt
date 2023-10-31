package com.yusril.pokemon.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemon")
data class FavoritePokemonEntity(
    var id: Int?,
    @PrimaryKey
    val name: String,
    val url: String?,
)
