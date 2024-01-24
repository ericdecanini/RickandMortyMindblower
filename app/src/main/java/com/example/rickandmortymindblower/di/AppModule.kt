package com.example.rickandmortymindblower.di

import com.example.rickandmortymindblower.repo.RickAndMortyApi
import com.example.rickandmortymindblower.repo.RickAndMortyApiFactory
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
