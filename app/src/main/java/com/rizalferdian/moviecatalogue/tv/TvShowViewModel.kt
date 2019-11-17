package com.rizalferdian.moviecatalogue.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.rizalferdian.moviecatalogue.service.AppService

class TvShowViewModel : ViewModel() {
    private val pagingConfig = Config(
        20,
        enablePlaceholders = false
    )
    private val dataSourceFactory = TvShowDataSourceFactory(AppService.create(), viewModelScope)

    val movieLive: LiveData<PagedList<TvShowModel>> = dataSourceFactory.toLiveData(pagingConfig)
    val refreshState = dataSourceFactory.sourceLive.switchMap {
        it.initialState
    }
}