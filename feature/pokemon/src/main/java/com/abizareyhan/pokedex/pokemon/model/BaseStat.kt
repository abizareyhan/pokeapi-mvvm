package com.abizareyhan.pokedex.pokemon.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.abizareyhan.pokedex.core.interfaces.BaseDiffUtilModel
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class BaseStat(
    val name: String,
    val amount: Int,
): BaseDiffUtilModel, Parcelable {
    override fun areContentsTheSame(toCompare: BaseDiffUtilModel?): Boolean {
        return if(toCompare is BaseStat) {
            val isSame = this.name == toCompare.name &&
                    this.amount == toCompare.amount
            isSame
        } else {
            false
        }
    }

    override fun areItemsTheSame(toCompare: BaseDiffUtilModel?): Boolean {
        return if(toCompare is BaseStat) {
            this.name == toCompare.name
        } else {
            false
        }
    }
}