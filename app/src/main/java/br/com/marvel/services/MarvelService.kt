package br.com.marvel.services

import br.com.marvel.models.Character
import br.com.marvel.models.MarvelResponse
import retrofit2.http.GET

interface MarvelService {

    @GET("public/characters")
    suspend fun getCharactersList(): MarvelResponse<Character>
}