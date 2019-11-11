package br.com.marvel.services

import retrofit2.http.GET

interface MarvelService {

    @GET("public/characters")
    suspend fun getCharactersList(): Any
}