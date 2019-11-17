package com.rizalferdian.moviecatalogue.favorite.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDao {
    @Insert
    fun insert(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movie")
    suspend fun getAll(): List<FavoriteMovie>

    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    suspend fun findById(id: Int)

    @Delete
    suspend fun delete(user: FavoriteMovie)
}