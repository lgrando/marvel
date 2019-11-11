package br.com.marvel.services

import br.com.marvel.models.Character
import br.com.marvel.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("public/characters")
    suspend fun getCharactersList(
        @Query("ts") ts: String = "1",
        @Query("apikey") apikey: String = "9a83f00bf44db447d10086271b011abb",
        @Query("hash") hash: String = "c5731f75a44f236db74d30993cda07fd"
    ): MarvelResponse<Character>
}