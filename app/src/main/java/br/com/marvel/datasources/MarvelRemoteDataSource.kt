package br.com.marvel.datasources

import br.com.marvel.services.MarvelService

class MarvelRemoteDataSource(private val service: MarvelService) {

    suspend fun getCharactersList(): Any {
        return service.getCharactersList()
    }
}