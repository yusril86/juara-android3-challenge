package com.yusril.trawlbenstest.data.mapper

import com.yusril.trawlbenstest.data.database.entity.PokemonEntity
import com.yusril.trawlbenstest.data.model.Pokemon

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(this.id, this.name, this.url)

fun List<Pokemon>.toPokemonEntityList(): List<PokemonEntity> = this.map { it.toPokemonEntity() }

fun Pokemon.toPokemonEntity(): PokemonEntity =
    PokemonEntity(this.id, this.name.toString(), this.url.toString())