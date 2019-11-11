package br.com.marvel.models

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)