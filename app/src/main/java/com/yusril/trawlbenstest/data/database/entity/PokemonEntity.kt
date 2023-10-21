package com.yusril.trawlbenstest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    var id: Int?,
    @PrimaryKey
    val name: String,
    val url: String?,
)