package com.example.rickandmortymindblower.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteEntity(
    @PrimaryKey val id: String,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val origin: String,
    val episodeDebut: String,
)
