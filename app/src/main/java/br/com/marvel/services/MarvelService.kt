package br.com.marvel.services

import br.com.marvel.models.Character
import br.com.marvel.models.Comic
import br.com.marvel.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {

    @GET("public/characters")
    suspend fun getCharactersList(): MarvelResponse<Character>

    @GET("public/characters/{id}/comics")
    suspend fun getCharacterComics(
        @Path("id") id: Long
    ): MarvelResponse<Comic>
}