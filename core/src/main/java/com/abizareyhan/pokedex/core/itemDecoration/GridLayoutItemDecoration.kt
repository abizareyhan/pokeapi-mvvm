package com.abizareyhan.pokedex.core.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridLayoutItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdges: Boolean,
    private val topSpacing: Int = 0
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if(includeEdges) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = if(topSpacing > 0) {
                    topSpacing
                } else {
                    spacing
                }
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = topSpacing
            }

            outRect.bottom = spacing
        }
    }
}