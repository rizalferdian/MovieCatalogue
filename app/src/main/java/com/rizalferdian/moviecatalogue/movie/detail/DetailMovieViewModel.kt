package com.rizalferdian.moviecatalogue.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalferdian.moviecatalogue.favorite.movie.FavoriteMovie
import com.rizalferdian.moviecatalogue.favorite.movie.FavoriteMovieDao
import com.rizalferdian.moviecatalogue.service.AppDatabase
import com.rizalferdian.moviecatalogue.service.AppResult
import com.rizalferdian.moviecatalogue.service.AppService
import kotlinx.coroutines.launch

class DetailMovieViewModel(movieId: Int, val dao: FavoriteMovieDao) : ViewModel() {
    private val _movieDetailState = MutableLiveData<AppResult<DetailMovieModel>>()
    val movieDetailState: LiveData<AppResult<DetailMovieModel>> = _movieDetailState

    init {
        fetchMovieDetail(movieId)
    }

    private fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            try {
                _movieDetailState.value = AppResult.loading()
                val result = AppService.create().getMovieDetail(movieId)
                _movieDetailState.value = AppResult.success(result)
            } catch (e: Exception) {
                _movieDetailState.value = AppResult.error(e.localizedMessage)
            }
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            val favoriteMovie = FavoriteMovie(, )
            dao.insert()
        }
    }

    fun resetfavorite() {

    }
}