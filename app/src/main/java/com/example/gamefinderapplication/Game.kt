package com.example.gamefinderapplication

import android.os.Parcel
import android.os.Parcelable

data class Game(
    val name: String,
    val description: String,
    val url: String,
    val genre: String,
    val price: Double,
    val platforms: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.createStringArrayList() ?: ArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(genre)
        parcel.writeDouble(price)
        parcel.writeStringList(platforms)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game = Game(parcel)
        override fun newArray(size: Int): Array<Game?> = arrayOfNulls(size)
    }
}
