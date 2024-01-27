package com.example.rickandmortymindblower.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM favourites")
    fun getAll(): Flow<List<FavouriteEntity>>

    @Query("SELECT * FROM favourites WHERE id=:id")
    fun getById(id: String): FavouriteEntity?

    @Insert
    suspend fun insert(favouriteEntity: FavouriteEntity)

    @Query("DELETE FROM favourites WHERE id=:id")
    suspend fun deleteById(id: String)
}
