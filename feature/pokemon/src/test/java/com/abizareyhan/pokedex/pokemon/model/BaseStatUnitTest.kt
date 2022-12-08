package com.abizareyhan.pokedex.pokemon.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BaseStatUnitTest {
    @Test
    fun testAreContentsTheSame_sameNameAndAmount() {
        val stat1 = BaseStat("strength", 10)
        val stat2 = BaseStat("strength", 10)
        assertTrue(stat1.areContentsTheSame(stat2))
    }

    @Test
    fun testAreContentsTheSame_differentNames() {
        val stat1 = BaseStat("strength", 10)
        val stat2 = BaseStat("agility", 10)
        assertFalse(stat1.areContentsTheSame(stat2))
    }

    @Test
    fun testAreContentsTheSame_differentAmounts() {
        val stat1 = BaseStat("strength", 10)
        val stat2 = BaseStat("strength", 20)
        assertFalse(stat1.areContentsTheSame(stat2))
    }

    @Test
    fun testAreItemsTheSame_sameName() {
        val stat1 = BaseStat("strength", 10)
        val stat2 = BaseStat("strength", 20)
        assertTrue(stat1.areItemsTheSame(stat2))
    }

    @Test
    fun testAreItemsTheSame_differentNames() {
        val stat1 = BaseStat("strength", 10)
        val stat2 = BaseStat("agility", 10)
        assertFalse(stat1.areItemsTheSame(stat2))
    }
}