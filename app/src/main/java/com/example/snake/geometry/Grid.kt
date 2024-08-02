package com.example.snake.geometry

import android.graphics.RectF

class Grid(
    private val rows: Int,
    private val columns: Int,
    private val width: Int,
    private val height: Int,
) {
    private val xRatio = width.toFloat() / rows
    private val yRatio = height.toFloat() / columns

    fun getRectAt(pos: Position): RectF {
        var xr = pos.x % rows
        var yr = pos.y % columns

        if (xr < 0) xr += rows
        if (yr < 0) yr += columns

        return RectF(
            xRatio * xr + 2,
            yRatio * yr + 2,
            xRatio * xr + xRatio - 2,
            yRatio * yr + yRatio - 2,
        )
    }

    fun getBoundingRect(): RectF {
        return RectF(0f, 0f, width.toFloat(), height.toFloat())
    }
}