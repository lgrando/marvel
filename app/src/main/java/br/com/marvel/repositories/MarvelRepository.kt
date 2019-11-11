package br.com.marvel.repositories

import br.com.marvel.datasources.MarvelRemoteDataSource
import br.com.marvel.models.Character
import br.com.marvel.models.MarvelResponse

class MarvelRepository(private val dataSource: MarvelRemoteDataSource) {

    suspend fun getCharactersList(): MarvelResponse<Character> {
        return dataSource.getCharactersList()
    }
}