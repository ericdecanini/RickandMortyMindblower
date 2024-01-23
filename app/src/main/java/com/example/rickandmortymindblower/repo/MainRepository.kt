package com.example.rickandmortymindblower.repo

import CharactersListQuery
import com.apollographql.apollo3.ApolloClient
import javax.inject.Inject

class MainRepository @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getCharacters() : List<Character> {
        var response = apolloClient.query(CharactersListQuery()).execute()

        return response.data?.characters?.results?.mapNotNull {
            Character(
                it?.id.orEmpty(),
                it?.name.orEmpty(),
                it?.status.orEmpty(),
                it?.species.orEmpty(),
                it?.image.orEmpty(),
            ) } ?: emptyList()
    }
}
