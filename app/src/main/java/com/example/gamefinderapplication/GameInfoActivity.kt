package com.example.gamefinderapplication


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class GameInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        @Suppress("DEPRECATION")
        val game = intent.getParcelableExtra<Game>("game")


        game?.let {
            findViewById<TextView>(R.id.gameName).text = it.name
            findViewById<TextView>(R.id.gameDescription).text = it.description
            // Load image using Glide or Picasso
        }
    }

    companion object {
        fun newIntent(context: Context, game: Game): Intent {
            val intent = Intent(context, GameInfoActivity::class.java)
            intent.putExtra("game", game)
            return intent
        }
    }

}
