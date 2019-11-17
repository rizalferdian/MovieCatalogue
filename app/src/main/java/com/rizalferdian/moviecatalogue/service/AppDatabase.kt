package com.rizalferdian.moviecatalogue.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizalferdian.moviecatalogue.favorite.movie.FavoriteMovie
import com.rizalferdian.moviecatalogue.favorite.movie.FavoriteMovieDao

@Database(entities = [FavoriteMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "app_database"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).build()
            }
            return instance!!
        }
    }

    abstract fun favoriteMovieDao(): FavoriteMovieDao
}