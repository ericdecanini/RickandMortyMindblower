package com.example.rickandmortymindblower.di

import com.example.rickandmortymindblower.data.api.RickAndMortyApi
import com.example.rickandmortymindblower.data.api.RickAndMortyApiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}
