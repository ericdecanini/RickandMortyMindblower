package com.example.rickandmortymindblower.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteEntity::class], version = 1, exportSchema = false)
abstract class MindblowerDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

    companion object {

        @Volatile
        private var _instance: MindblowerDatabase? = null

        fun getDatabase(context: Context): MindblowerDatabase {
            val tempInstance = _instance
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MindblowerDatabase::class.java,
                    "mindblowerDatabase.db"
                ).build()
                _instance = instance
                return instance
            }
        }
    }
}
