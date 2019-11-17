package com.rizalferdian.moviecatalogue.tv

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.rizalferdian.moviecatalogue.service.AppService
import kotlinx.coroutines.CoroutineScope

class TvShowDataSourceFactory(
    private val service: AppService,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, TvShowModel>() {
    val sourceLive = MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Int, TvShowModel> {
        val source = TvShowDataSource(service, coroutineScope)
        sourceLive.postValue(source)
        return source
    }
}