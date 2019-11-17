package com.rizalferdian.moviecatalogue.service

import com.google.gson.GsonBuilder
import com.rizalferdian.moviecatalogue.BuildConfig
import com.rizalferdian.moviecatalogue.movie.MovieModel
import com.rizalferdian.moviecatalogue.movie.detail.DetailMovieModel
import com.rizalferdian.moviecatalogue.tv.TvShowModel
import com.rizalferdian.moviecatalogue.tv.detail.DetailTvShowModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AppService {
    companion object {
        private val retrofit by lazy {
            val interceptor = AppInterceptor()
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        private val appService by lazy {
            retrofit.create(AppService::class.java)
        }

        fun create(): AppService {
            return appService
        }
    }

    @GET("movie/popular")
    suspend fun getMovie(
        @Query("page") page: Int
    ): AppResponse<List<MovieModel>>


    @GET("movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Int
    ): DetailMovieModel


    @GET("tv/popular")
    suspend fun getTvShow(
        @Query("page") page: Int
    ): AppResponse<List<TvShowModel>>

    @GET("tv/{tvId}")
    suspend fun getTvShowDetail(
        @Path("tvId") tvId: Int
    ): DetailTvShowModel
}