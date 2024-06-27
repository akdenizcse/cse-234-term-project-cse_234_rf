package com.example.gamefinderapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    private lateinit var profilePicture: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var passwordTextView: TextView
    private lateinit var editProfileButton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profilePicture = view.findViewById(R.id.profilePicture)
        nameTextView = view.findViewById(R.id.nameTextView)
        emailTextView = view.findViewById(R.id.emailTextView)
        passwordTextView = view.findViewById(R.id.passwordTextView)
        editProfileButton = view.findViewById(R.id.editProfileButton)


        editProfileButton.setOnClickListener {
            //edit profile add later
        }

        // mock
        nameTextView.text = "John Doe"
        emailTextView.text = "john.doe@example.com"


        val imageUrl = "https://images.ctfassets.net/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=1200&h=992&fl=progressive&q=70&fm=jpg"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.default_profile_image) // Optional placeholder image
            .error(R.drawable.error_image) // Optional error image
            .into(profilePicture)

        return view
    }
}
