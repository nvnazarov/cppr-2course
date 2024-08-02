package com.example.snake

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
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
        val startBtn = findViewById<Button>(R.id.btn_start)

        startBtn.setOnClickListener {
            startBtn.setText(R.string.restart)
            game = Game(gameView, width = 20, height = 20, updatePeriod = 150L)
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