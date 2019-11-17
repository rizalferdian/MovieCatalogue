package com.rizalferdian.moviecatalogue.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.rizalferdian.moviecatalogue.service.AppService
import kotlinx.coroutines.CoroutineScope

class MovieDataSourceFactory(
    private val service: AppService,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, MovieModel>() {
    val sourceLive = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieModel> {
        val source = MovieDataSource(service, coroutineScope)
        sourceLive.postValue(source)
        return source
    }
}