package com.abizareyhan.pokedex.core.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecorator(
    private val spacing: Int,
    private val includeFirst: Boolean = false,
    private val includeLast: Boolean = false
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.top = if(includeFirst) spacing else 0
                outRect.bottom = spacing / 2
            }
            state.itemCount - 1 -> {
                outRect.bottom = if(includeLast) spacing else 0
            }
            else -> {
                outRect.top = 0
                outRect.bottom = spacing / 2
            }
        }
    }
}