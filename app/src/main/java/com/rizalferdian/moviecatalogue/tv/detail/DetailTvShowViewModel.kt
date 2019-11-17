package com.rizalferdian.moviecatalogue.tv.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rizalferdian.moviecatalogue.service.AppResult
import com.rizalferdian.moviecatalogue.service.AppService
import kotlinx.coroutines.launch

class DetailTvShowViewModel(tvId: Int) : ViewModel() {
    private val _movieDetailState = MutableLiveData<AppResult<DetailTvShowModel>>()
    val movieDetailState: LiveData<AppResult<DetailTvShowModel>> = _movieDetailState

    init {
        fetchTvShowDetail(tvId)
    }

    private fun fetchTvShowDetail(tvId: Int) {
        viewModelScope.launch {
            try {
                _movieDetailState.value = AppResult.loading()
                val result = AppService.create().getTvShowDetail(tvId)
                _movieDetailState.value = AppResult.success(result)
            } catch (e: Exception) {
                _movieDetailState.value = AppResult.error(e.localizedMessage)
            }
        }
    }
}