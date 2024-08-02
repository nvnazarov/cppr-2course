package com.example.snake.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var onDrawCallback: ((Canvas) -> Unit)? = null

    private val framePaint = Paint()

    init {
        framePaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        onDrawCallback?.invoke(canvas)
    }
}