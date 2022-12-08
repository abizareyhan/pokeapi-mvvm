package com.abizareyhan.pokedex.pokemon.viewholder

import android.content.res.ColorStateList
import android.graphics.Color
import com.abizareyhan.pokedex.core.base.BaseViewHolder
import com.abizareyhan.pokedex.core.R
import com.abizareyhan.pokedex.feature.pokemon.databinding.ItemStatsBinding
import com.abizareyhan.pokedex.pokemon.model.BaseStat

class PokemonStatsViewHolder(
    private val itemBinding: ItemStatsBinding,
    private val baseColor: Color
): BaseViewHolder<BaseStat>(itemBinding) {
    override fun bind(model: BaseStat) {
        with(itemBinding) {
            tvName.setTextColor(baseColor.toArgb())
            tvName.text = when(model.name) {
                "hp" -> root.context.getString(R.string.hp)
                "attack" -> root.context.getString(R.string.atk)
                "defense" -> root.context.getString(R.string.def)
                "special-attack" -> root.context.getString(R.string.satk)
                "special-defense" -> root.context.getString(R.string.sdef)
                "speed" -> root.context.getString(R.string.spd)
                else -> ""
            }
            tvAmount.text = model.amount.toString().padStart(3, '0')

            progressAmount.progress = model.amount
            progressAmount.max = 255
            progressAmount.progressTintList = ColorStateList.valueOf(baseColor.toArgb())
            progressAmount.progressBackgroundTintList  = ColorStateList.valueOf(
                Color.argb(0.2F, baseColor.red(), baseColor.green(), baseColor.blue())
            )
        }
    }
}