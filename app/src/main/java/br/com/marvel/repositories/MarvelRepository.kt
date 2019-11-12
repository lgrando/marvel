package br.com.marvel.repositories

import br.com.marvel.datasources.MarvelRemoteDataSource
import br.com.marvel.models.Character
import br.com.marvel.models.Comic
import br.com.marvel.models.MarvelResponse

class MarvelRepository(private val dataSource: MarvelRemoteDataSource) {

    suspend fun getCharactersList(offset: Int): MarvelResponse<Character> {
        return dataSource.getCharactersList(offset)
    }

    suspend fun getCharacterComicsList(characterId: Long): MarvelResponse<Comic> {
        return dataSource.getCharacterComicsList(characterId)
    }
}