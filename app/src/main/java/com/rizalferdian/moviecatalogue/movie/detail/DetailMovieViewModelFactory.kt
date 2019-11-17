package com.rizalferdian.moviecatalogue.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailMovieViewModelFactory(val movieId: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            DetailMovieViewModel(movieId) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}