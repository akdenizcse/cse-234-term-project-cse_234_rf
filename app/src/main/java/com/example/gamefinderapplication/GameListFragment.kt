package com.example.gamefinderapplication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private val gameList = listOf(
        Game("Game 1", "", "url1", "Genre 1", 19.99, listOf("Platform 1", "Platform 2")),
        Game("Game 2", "", "url2", "Genre 2", 29.99, listOf("Platform 3", "Platform 4"))
        // Add more games with appropriate values for genre, price, and platforms
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        gameAdapter = GameAdapter(gameList) { game: Game ->
            val intent = GameInfoActivity.newIntent(requireContext(), game)
            startActivity(intent)
        }
        recyclerView.adapter = gameAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }
}
