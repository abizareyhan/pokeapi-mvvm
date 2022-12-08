package com.abizareyhan.pokedex.core.extension

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setItemDecoration(itemDecoration: RecyclerView.ItemDecoration) {
    if(this.itemDecorationCount > 0) {
        this.removeItemDecorationAt(0)
    }

    this.addItemDecoration(itemDecoration)
}