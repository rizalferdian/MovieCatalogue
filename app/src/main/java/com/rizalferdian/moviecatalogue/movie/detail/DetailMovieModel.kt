package com.rizalferdian.moviecatalogue.movie.detail

import com.google.gson.annotations.SerializedName
import java.util.*

data class DetailMovieModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("revenue")
    val revenue: Int,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("genres")
    val genres: List<GenreModel>
)