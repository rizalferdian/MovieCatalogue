package com.rizalferdian.moviecatalogue.tv.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailTvShowViewModelFactory(val tvId: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailTvShowViewModel::class.java)) {
            DetailTvShowViewModel(tvId) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}