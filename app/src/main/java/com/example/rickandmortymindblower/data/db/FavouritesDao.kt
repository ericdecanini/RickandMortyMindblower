package com.example.rickandmortymindblower.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourites")
    suspend fun getAll(): List<FavouriteEntity>

    @Insert
    suspend fun insert(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun delete(favouriteEntity: FavouriteEntity)
}
