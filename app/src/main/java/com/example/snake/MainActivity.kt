package com.example.snake

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.snake.game.Game
import com.example.snake.geometry.Direction
import com.example.snake.views.GameView

class MainActivity : ComponentActivity(), OnClickListener {
    private var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_main)

        val gameView = findViewById<GameView>(R.id.game_view)
        val editWidth = findViewById<EditText>(R.id.edit_width)
        val editHeight = findViewById<EditText>(R.id.edit_height)
        val startBtn = findViewById<Button>(R.id.btn_start)

        startBtn.setOnClickListener {
            var width: Int
            var height: Int
            try {
                width = editWidth.text.toString().toInt()
                height = editHeight.text.toString().toInt()

                if (width <= 4 || height <= 4) throw IllegalArgumentException()
            } catch (e: Exception) {
                width = 10
                height = 10
                editWidth.setText(width.toString())
                editHeight.setText(height.toString())
            }

            startBtn.setText(R.string.restart)
            game = Game(
                gameView,
                width,
                height,
                updatePeriod = 150L
            )
            game?.start()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.left -> game?.snake?.direction = Direction.LEFT
            R.id.right -> game?.snake?.direction = Direction.RIGHT
            R.id.up -> game?.snake?.direction = Direction.UP
            R.id.down -> game?.snake?.direction = Direction.DOWN
            else -> {}
        }
    }
}