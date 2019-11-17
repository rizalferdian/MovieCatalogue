package com.rizalferdian.moviecatalogue.tv

import com.google.gson.annotations.SerializedName

data class TvShowModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String
)