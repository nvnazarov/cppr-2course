package com.example.snake.game

import com.example.snake.geometry.Direction
import com.example.snake.geometry.Position

class Snake {
    var direction = Direction.RIGHT
        set(value) {
            if (value == Direction.UP && direction == Direction.DOWN
                || value == Direction.DOWN && direction == Direction.UP
                || value == Direction.LEFT && direction == Direction.RIGHT
                || value == Direction.RIGHT && direction == Direction.LEFT) {
                return
            }
            field = value
        }
    val body: MutableList<Position> = ArrayList()
    private var additionalSegmentsCount = 0

    // add snake head
    init {
        body.add(Position(0, 0))
    }

    val head: Position
        get() = body[0]

    /**
     * Move snake by 1 cell in the [direction].
     * Add one body segment if was requested by
     * [addBodySegment].
     */
    fun move() {
        var i = body.size - 1
        val tail = body[i].copy()
        while (i > 0) {
            body[i] = body[i - 1].copy()
            i--
        }

        when (direction) {
            Direction.LEFT -> body[0].x--
            Direction.RIGHT -> body[0].x++
            Direction.UP -> body[0].y--
            Direction.DOWN -> body[0].y++
        }

        if (additionalSegmentsCount > 0) {
            body.add(tail)
            additionalSegmentsCount--
        }
    }

    /**
     * Add body segment on next move.
     * New segment will appear where
     * the tail was.
     */
    fun addBodySegment() = additionalSegmentsCount++
}