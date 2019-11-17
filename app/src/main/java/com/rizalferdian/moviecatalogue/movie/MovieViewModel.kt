package com.rizalferdian.moviecatalogue.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.rizalferdian.moviecatalogue.service.AppService

class MovieViewModel : ViewModel() {
    private val pagingConfig = Config(
        20,
        enablePlaceholders = false
    )
    private val dataSourceFactory = MovieDataSourceFactory(AppService.create(), viewModelScope)

    val movieLive: LiveData<PagedList<MovieModel>> = dataSourceFactory.toLiveData(pagingConfig)
    val refreshState = dataSourceFactory.sourceLive.switchMap {
        it.initialState
    }
}