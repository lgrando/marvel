package br.com.marvel.repositories

import br.com.marvel.datasources.MarvelRemoteDataSource

class MarvelRepository(private val dataSource: MarvelRemoteDataSource) {

    suspend fun getCharactersList(): Any {
        return dataSource.getCharactersList()
    }
}