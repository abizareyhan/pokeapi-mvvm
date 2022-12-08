package com.abizareyhan.pokedex.pokemon.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.abizareyhan.pokedex.core.interfaces.BaseDiffUtilModel
import com.abizareyhan.pokedex.domain.entity.PokemonEntity
import com.abizareyhan.pokedex.pokemon.enums.PokemonType
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Pokemon(
    val id: Int = -1,
    val thumbnailURL: String = "",
    val name: String = "",
    val pokemonTypes: List<PokemonType> = listOf(),
    val weight: Long = 0,
    val height: Long = 0,
    val abilities: List<String> = listOf(),
    val description: String = "",
    val baseStats: List<BaseStat> = listOf(),
    val evolutions: List<Pokemon> = listOf()
): BaseDiffUtilModel, Parcelable {
    constructor(pokemonEntity: PokemonEntity): this(
        id = pokemonEntity.id,
        thumbnailURL = pokemonEntity.thumbnail,
        name = pokemonEntity.name,
        pokemonTypes = pokemonEntity.types.map { entity ->
            PokemonType.values().firstOrNull { type ->
                type.name.equals(entity.name, true)
            } ?: PokemonType.NORMAL
        },
        weight = pokemonEntity.weight,
        height = pokemonEntity.height,
        abilities = pokemonEntity.abilities,
        baseStats = pokemonEntity.baseStats.map {
            BaseStat(
                name = it.first,
                amount = it.second
            )
        },
        description = pokemonEntity.description,
        evolutions = pokemonEntity.evolutionChainList.map {
            Pokemon(it.pokemonEntity)
        }
    )


    val hexID: String
        get() {
            return "#${id.toString().padStart(3, '0')}"
        }

    val weightInKg: String
        get() {
            return weight.div(10.0).toString()
        }

    val heightInMeter: String
        get() {
            return height.div(10.0).toString()
        }

    val nextPokemonId: Int?
        get() {
            return when (id) {
                in 1..905 -> {
                    id + 1
                }
                905 -> {
                    10001
                }
                in 10001..10249 -> {
                    id + 1
                }
                else -> {
                    null
                }
            }
        }

    val prevPokemonId: Int?
        get() {
            return when (id) {
                in 2..906 -> {
                    id - 1
                }
                10001 -> {
                    905
                }
                in 10002..10250 -> {
                    id - 1
                }
                else -> {
                    null
                }
            }
        }

    override fun areItemsTheSame(toCompare: BaseDiffUtilModel?): Boolean {
        return if(toCompare is Pokemon) {
            this.id == toCompare.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(toCompare: BaseDiffUtilModel?): Boolean {
        return if(toCompare is Pokemon) {
            val isSame = this.id == toCompare.id &&
                    this.thumbnailURL == toCompare.thumbnailURL &&
                    this.name == toCompare.name &&
                    this.pokemonTypes == this.pokemonTypes &&
                    this.weight == this.weight &&
                    this.height == this.height &&
                    this.abilities == this.abilities &&
                    this.description == this.description &&
                    this.baseStats == this.baseStats &&
                    this.evolutions == this.evolutions
            isSame
        } else {
            false
        }
    }
}