package com.example.rickandmortymindblower.di

import com.example.rickandmortymindblower.data.repo.CharactersRepository
import com.example.rickandmortymindblower.data.repo.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsCharactersRepository(impl: CharactersRepositoryImpl): CharactersRepository
}
