package br.com.marvel.models

import com.google.gson.annotations.SerializedName

data class MarvelResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: Data<T>
) {
    class Data<T>(
        @SerializedName("offset") val offset: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("results") val results: List<T>
    )
}