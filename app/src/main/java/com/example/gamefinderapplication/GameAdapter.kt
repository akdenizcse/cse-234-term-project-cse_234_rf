package com.example.gamefinderapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GameAdapter(private val gameList: List<Game>, private val clickListener: (Game) -> Unit) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameName: TextView = view.findViewById(R.id.gameName)
        val gameGenre: TextView = view.findViewById(R.id.gameGenre)
        val gamePrice: TextView = view.findViewById(R.id.gamePrice)
        val gamePlatforms: TextView = view.findViewById(R.id.gamePlatforms)
        // Add other TextViews for additional game data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gameItem = gameList[position]
        holder.gameName.text = gameItem.name
        holder.gameGenre.text = gameItem.genre
        holder.gamePrice.text = "$${gameItem.price}"
        holder.gamePlatforms.text = gameItem.platforms.joinToString(", ")
        // Set other views with game data

        holder.itemView.setOnClickListener {
            val clickedGame = gameList[position]
            clickListener(clickedGame)
        }
    }


    override fun getItemCount() = gameList.size
}
