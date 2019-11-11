package br.com.marvel.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Thumbnail(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val extension: String
) : Serializable {

    fun getThumbnailUrl(): String = "$path.$extension"
}