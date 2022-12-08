package com.abizareyhan.pokedex.pokemon.model

import com.abizareyhan.pokedex.domain.entity.EvolutionChainEntity
import com.abizareyhan.pokedex.domain.entity.PokemonEntity
import com.abizareyhan.pokedex.domain.entity.PokemonTypeEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class PokemonUnitTest {
    @Test
    fun constructor_mapsPokemonEntityToPokemonCorrectly() {
        // Create an instance of the PokemonEntity class to use as sample input
        val pokemonEntity = PokemonEntity(
            id = 123,
            name = "Pokemon 123",
            thumbnail = "Thumbnail 123",
            types = listOf(
                PokemonTypeEntity(
                    name = "The Type 999"
                ),
                PokemonTypeEntity(
                    name = "The Type 777"
                )
            ),
            weight = 123,
            height = 321,
            abilities = listOf("Ab", "il", "it", "ies"),
            baseStats = listOf(
                Pair("HP", 100),
                Pair("ATK", 200),
                Pair("DEF", 300),
            ),
            description = "This is a pokemon test",
            evolutionChainList = listOf(
                EvolutionChainEntity(
                    PokemonEntity(
                        id = 456,
                        name = "Pokemon 456",
                        thumbnail = "Thumbnail 456",
                        types = listOf(
                            PokemonTypeEntity(
                                name = "The Type 923"
                            ),
                            PokemonTypeEntity(
                                name = "The Type 876"
                            )
                        ),
                    ),
                ),
                EvolutionChainEntity(
                    PokemonEntity(
                        id = 457,
                        name = "Pokemon 457",
                        thumbnail = "Thumbnail 457",
                        types = listOf(
                            PokemonTypeEntity(
                                name = "The Type 922"
                            ),
                            PokemonTypeEntity(
                                name = "The Type 875"
                            )
                        ),
                    ),
                )
            )
        )

        val pokemon = Pokemon(pokemonEntity)

        assertEquals(123, pokemon.id)
        assertEquals("Thumbnail 123", pokemon.thumbnailURL)
        assertEquals("Pokemon 123", pokemon.name)
    }

    @Test
    fun hexID_returnsExpectedResult() {
        val pokemon = Pokemon(id = 1)
        assertEquals("#001", pokemon.hexID)
    }

    @Test
    fun weightInKg_returnsExpectedResult() {
        val pokemon = Pokemon(weight = 100)
        assertEquals("10.0", pokemon.weightInKg)
    }

    @Test
    fun heightInMeter_returnsExpectedResult() {
        val pokemon = Pokemon(height = 100)
        assertEquals("10.0", pokemon.heightInMeter)
    }

    @Test
    fun nextPokemonId_returnsExpectedResult() {
        val pokemon = Pokemon(id = 1)
        assertEquals(2, pokemon.nextPokemonId)
    }

    @Test
    fun prevPokemonId_returnsExpectedResult() {
        val pokemon = Pokemon(id = 2)
        assertEquals(1, pokemon.prevPokemonId)
    }
}