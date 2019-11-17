package com.rizalferdian.moviecatalogue.tv.detail

import com.google.gson.annotations.SerializedName
import com.rizalferdian.moviecatalogue.movie.detail.GenreModel
import java.util.*

data class DetailTvShowModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("first_air_date")
    val firstAirDate: Date,
    @SerializedName("genres")
    val genres: List<GenreModel>
)