package br.com.marvel.datasources

import br.com.marvel.models.Character
import br.com.marvel.models.Comic
import br.com.marvel.models.MarvelResponse
import br.com.marvel.services.MarvelService

class MarvelRemoteDataSource(private val service: MarvelService) {

    suspend fun getCharactersList(offset: Int): MarvelResponse<Character> {
        return service.getCharactersList(offset)
    }

    suspend fun getCharacterComicsList(characterId: Long): MarvelResponse<Comic> {
        return service.getCharacterComics(characterId)
    }

}