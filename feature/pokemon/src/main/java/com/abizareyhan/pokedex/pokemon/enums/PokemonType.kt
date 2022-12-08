package com.abizareyhan.pokedex.pokemon.enums

import androidx.annotation.ColorRes
import androidx.annotation.Keep
import androidx.annotation.StringRes
import com.abizareyhan.pokedex.core.R
import com.abizareyhan.pokedex.core.interfaces.BaseDiffUtilModel

@Keep
enum class PokemonType(
    @StringRes val nameResource: Int,
    @ColorRes val colorResource: Int
): BaseDiffUtilModel {
    ROCK(
        R.string.type_rock,
        R.color.type_rock
    ),
    GHOST(
        R.string.type_ghost,
        R.color.type_ghost
    ),
    STEEL(
        R.string.type_steel,
        R.color.type_steel
    ),
    WATER(
        R.string.type_water,
        R.color.type_water
    ),
    GRASS(
        R.string.type_grass,
        R.color.type_grass
    ),
    PSYCHIC(
        R.string.type_psychic,
        R.color.type_psychic
    ),
    ICE(
        R.string.type_ice,
        R.color.type_ice
    ),
    DARK(
        R.string.type_dark,
        R.color.type_dark
    ),
    FAIRY(
        R.string.type_fairy,
        R.color.type_fairy
    ),
    NORMAL(
        R.string.type_normal,
        R.color.type_normal
    ),
    FIGHTING(
        R.string.type_fighting,
        R.color.type_fighting
    ),
    FLYING(
        R.string.type_flying,
        R.color.type_flying
    ),
    POISON(
        R.string.type_poison,
        R.color.type_poison
    ),
    GROUND(
        R.string.type_ground,
        R.color.type_ground
    ),
    BUG(
        R.string.type_bug,
        R.color.type_bug
    ),
    FIRE(
        R.string.type_fire,
        R.color.type_fire
    ),
    ELECTRIC(
        R.string.type_electric,
        R.color.type_electric
    ),
    DRAGON(
        R.string.type_dragon,
        R.color.type_dragon
    ),












}