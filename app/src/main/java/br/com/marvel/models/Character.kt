package br.com.marvel.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
) : Serializable