package com.example.snake.game

import android.graphics.Canvas
import android.graphics.Paint
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.snake.geometry.Grid
import com.example.snake.geometry.Position
import com.example.snake.views.GameView

class Game(
    private val view: GameView,
    private val width: Int = 10,
    private val height: Int = 10,
    private val updatePeriod: Long = 100L,
) {
    val snake: Snake = Snake()

    private val snakePaint = Paint()
    private val cherryPaint = Paint()
    private val framePaint = Paint()

    init {
        cherryPaint.color = Color.Red.toArgb()
        snakePaint.color = Color.Green.toArgb()
        framePaint.style = Paint.Style.STROKE
    }

    fun start() {
        val random = java.util.Random()
        var cherryPos = Position(random.nextInt(), random.nextInt())

        val drawEntities = { canvas: Canvas ->
            val grid = Grid(width, height, canvas.width, canvas.width)

            canvas.drawRect(grid.getBoundingRect(), framePaint)

            val cherryRect = grid.getRectAt(cherryPos)
            canvas.drawRect(cherryRect, cherryPaint)

            for (pos in snake.body) {
                val rect = grid.getRectAt(pos)
                canvas.drawRect(rect, snakePaint)
            }
        }

        view.onDrawCallback = drawEntities

        val timer = object : CountDownTimer(Long.MAX_VALUE, updatePeriod) {
            override fun onTick(tick: Long) {
                snake.move()

                if (isSnakeHeadCollidedWithBody()) {
                    this.cancel()
                }

                if (isSnakeCollidedWith(cherryPos)) {
                    snake.addBodySegment()
                    cherryPos = Position(random.nextInt(), random.nextInt())
                }

                view.invalidate()
            }

            override fun onFinish() {}
        }
        timer.start()
    }

    private fun isSnakeCollidedWith(pos: Position): Boolean {
        for (bodyPos in snake.body) {
            if (bodyPos.eq(pos)) {
                return true
            }
        }
        return false
    }

    private fun isSnakeHeadCollidedWithBody(): Boolean {
        for (bodyPos in snake.body.drop(1)) {
            if (snake.head.eq(bodyPos)) {
                return true
            }
        }
        return false
    }

    private fun Position.eq(pos: Position): Boolean {
        return (pos.x - this.x) % width == 0 && (pos.y - this.y) % height == 0
    }
}