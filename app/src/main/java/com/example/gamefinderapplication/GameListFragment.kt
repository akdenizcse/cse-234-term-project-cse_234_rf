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
        Game("Red Dead Redemption 2",
            "Winner of over 175 Game of the Year Awards and recipient of over 250 perfect scores, RDR2 is the epic tale of outlaw Arthur Morgan and the infamous Van der Linde gang, on the run across America at the dawn of the modern age. Also includes access to the shared living world of Red Dead Online.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEDO5luWg5TvlOxZ-0IAtxzLzRWYY9GmF7PA&s",
            "Open-World",
            59.99,
            listOf("PS 4", "Xbox One","PC")),
        Game("Paladins",
            "Paladins is the ultimate fantasy team-based shooter experience, with over 50 customizable Champions fighting in 5v5 action across a diverse Realm of modes and maps!",
            "https://image.api.playstation.com/vulcan/img/rnd/202201/0414/gXlQ9CTsFCyKoVUjRNEYC3v1.png",
            "FPS",
            0.0,
            listOf("Nintendo Switch", "PS 4","PS 5", "PC"))
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
