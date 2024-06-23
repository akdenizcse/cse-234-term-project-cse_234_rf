package com.example.gamefinderapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class GameInfoActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_info)

        @Suppress("DEPRECATION")
        val game = intent.getParcelableExtra<Game>("game")

        game?.let {
            findViewById<TextView>(R.id.gameName).text = it.name
            findViewById<TextView>(R.id.gameGenre).text = it.genre
            findViewById<TextView>(R.id.gameDescription).text = it.description
            findViewById<TextView>(R.id.gamePlatforms).text = it.platforms.joinToString(", ")
            findViewById<TextView>(R.id.gamePrice).text = "$${it.price}"

            // Load image using Glide
            val gameImage = findViewById<ImageView>(R.id.gameImage)
            Glide.with(this)
                .load(it.url)
                .placeholder(R.drawable.default_game_image) // Add a placeholder image in case the image fails to load
                .into(gameImage)
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
