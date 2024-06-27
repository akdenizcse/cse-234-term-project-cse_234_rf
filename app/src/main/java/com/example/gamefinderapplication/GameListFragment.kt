package com.example.gamefinderapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class GameListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gameAdapter: GameAdapter
    private lateinit var searchBar: EditText
    private lateinit var filterButton: Button
    private lateinit var filterOptions: LinearLayout
    private lateinit var genreSpinner: Spinner
    private lateinit var platformPS4: CheckBox
    private lateinit var platformXboxOne: CheckBox
    private lateinit var platformPC: CheckBox
    private lateinit var priceSeekBar: SeekBar
    private lateinit var priceValue: TextView

    private val gameList = listOf(
        Game("Red Dead Redemption 2",
            "Winner of over 175 Game of the Year Awards and recipient of over 250 perfect scores, RDR2 is the epic tale of outlaw Arthur Morgan and the infamous Van der Linde gang, on the run across America at the dawn of the modern age. Also includes access to the shared living world of Red Dead Online.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSEDO5luWg5TvlOxZ-0IAtxzLzRWYY9GmF7PA&s",
            "Open-World",
            59.99,
            listOf("PS 4", "Xbox One", "PC")),
        Game("Paladins",
        "Paladins is the ultimate fantasy team-based shooter experience, with over 50 customizable Champions fighting in 5v5 action across a diverse Realm of modes and maps!",
        "https://image.api.playstation.com/vulcan/img/rnd/202201/0414/gXlQ9CTsFCyKoVUjRNEYC3v1.png",
        "FPS",
        0.0,
        listOf("Nintendo Switch", "PS 4", "PS 5", "PC")),
        Game("Outlast 2",
            "Outlast 2 introduces you to Sullivan Knoth and his followers, who left our wicked world behind to give birth to Temple Gate, a town, deep in the wilderness and hidden from civilization. Knoth and his flock are preparing for the tribulations of the end of times and you’re right in the thick of it.",
            "https://i.ytimg.com/vi/J7_poBJY138/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLCCwVpKsAkJamJn9WxPVTBR6PhLFQ",
            "Horror",
            19.99,
            listOf("PC")),
        Game("Grand Theft Auto V",
            "Grand Theft Auto V for PC offers players the option to explore the award-winning world of Los Santos and Blaine County in resolutions of up to 4k and beyond, as well as the chance to experience the game running at 60 frames per second.",
            "https://upload.wikimedia.org/wikipedia/en/a/a5/Grand_Theft_Auto_V.png",
            "Open-World",
            39.99,
            listOf( "Xbox One", "PC")),
        Game("Far Cry 6",
            "Enter the adrenaline-filled world of a modern-day guerrilla revolution. With stunning vistas, visceral gunplay, and a huge variety of gameplay experiences, there's never been a better time to join the fight.",
            "https://image.api.playstation.com/vulcan/img/rnd/202106/0722/4MxzDZKZwtEWyMWZghvwd7bQ.jpg",
            "FPS",
            49.99,
            listOf("PS 4", "Xbox One", "PC")),



    )

    private var filteredList: List<Game> = gameList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        searchBar = view.findViewById(R.id.searchBar)
        filterButton = view.findViewById(R.id.filterButton)
        filterOptions = view.findViewById(R.id.filterOptions)
        genreSpinner = view.findViewById(R.id.genreSpinner)
        platformPS4 = view.findViewById(R.id.platformPS4)
        platformXboxOne = view.findViewById(R.id.platformXboxOne)
        platformPC = view.findViewById(R.id.platformPC)
        priceSeekBar = view.findViewById(R.id.priceSeekBar)
        priceValue = view.findViewById(R.id.priceValue)
        recyclerView = view.findViewById(R.id.recyclerView)

        setupGenreSpinner()
        setupFilters()
        setupSearch()

        filterButton.setOnClickListener {
            if (filterOptions.visibility == View.GONE) {
                filterOptions.visibility = View.VISIBLE
            } else {
                filterOptions.visibility = View.GONE
                filterGames()
            }
        }

        gameAdapter = GameAdapter(filteredList) { game: Game ->
            val intent = GameInfoActivity.newIntent(requireContext(), game)
            startActivity(intent)
        }
        recyclerView.adapter = gameAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

    private fun setupGenreSpinner() {
        val genres = listOf("All", "Open-World", "FPS")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genres)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genreSpinner.adapter = adapter
    }

    private fun setupFilters() {
        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterGames()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        platformPS4.setOnCheckedChangeListener { _, _ -> filterGames() }
        platformXboxOne.setOnCheckedChangeListener { _, _ -> filterGames() }
        platformPC.setOnCheckedChangeListener { _, _ -> filterGames() }

        priceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                priceValue.text = "Max Price: $$progress"
                filterGames()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupSearch() {
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim().toLowerCase(Locale.getDefault())
                filteredList = if (searchText.isEmpty()) {
                    gameList.filter { true } // Show all if search text is empty
                } else {
                    gameList.filter { game ->
                        game.name.toLowerCase(Locale.getDefault()).contains(searchText)
                    }
                }
                gameAdapter.updateGames(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterGames() {
        val selectedGenre = genreSpinner.selectedItem.toString()
        val maxPrice = priceSeekBar.progress.toDouble()
        val selectedPlatforms = mutableListOf<String>()
        if (platformPS4.isChecked) selectedPlatforms.add("PS 4")
        if (platformXboxOne.isChecked) selectedPlatforms.add("Xbox One")
        if (platformPC.isChecked) selectedPlatforms.add("PC")
        val searchQuery = searchBar.text.toString().trim().toLowerCase(Locale.getDefault())

        filteredList = gameList.filter { game ->
            (selectedGenre == "All" || game.genre == selectedGenre) &&
                    game.price <= maxPrice &&
                    (selectedPlatforms.isEmpty() || selectedPlatforms.any { it in game.platforms }) &&
                    game.name.toLowerCase(Locale.getDefault()).contains(searchQuery)
        }

        gameAdapter.updateGames(filteredList)
    }
}
