package com.example.rickandmortymindblower.di

import android.content.Context
import com.example.rickandmortymindblower.data.api.RickAndMortyApi
import com.example.rickandmortymindblower.data.api.RickAndMortyApiFactory
import com.example.rickandmortymindblower.data.db.FavouritesDao
import com.example.rickandmortymindblower.data.db.MindblowerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRickAndMortyApi(
        rickAndMortyApiFactory: RickAndMortyApiFactory,
    ): RickAndMortyApi {
        return rickAndMortyApiFactory.create()
    }

    @Provides
    fun provideFavouritesDao(
        db: MindblowerDatabase,
    ): FavouritesDao {
        return db.favouritesDao()
    }

    @Provides
    fun provideMindblowerDatabase(
        @ApplicationContext context: Context,
    ): MindblowerDatabase {
        return MindblowerDatabase.getDatabase(context)
    }
}
