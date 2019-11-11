package br.com.marvel.datasources

import br.com.marvel.models.Character
import br.com.marvel.models.MarvelResponse
import br.com.marvel.services.MarvelService

class MarvelRemoteDataSource(private val service: MarvelService) {

    suspend fun getCharactersList(): MarvelResponse<Character> {
        return service.getCharactersList()
    }
}