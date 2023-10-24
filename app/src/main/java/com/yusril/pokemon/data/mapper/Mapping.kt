package com.yusril.pokemon.data.mapper

import com.yusril.pokemon.data.database.entity.PokemonEntity
import com.yusril.pokemon.data.model.Pokemon

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(this.id, this.name, this.url)

fun List<Pokemon>.toPokemonEntityList(): List<PokemonEntity> = this.map { it.toPokemonEntity() }

fun Pokemon.toPokemonEntity(): PokemonEntity =
    PokemonEntity(this.id, this.name.toString(), this.url.toString())