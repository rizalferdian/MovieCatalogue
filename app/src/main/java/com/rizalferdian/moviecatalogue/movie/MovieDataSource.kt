package com.rizalferdian.moviecatalogue.movie

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.rizalferdian.moviecatalogue.service.AppResult
import com.rizalferdian.moviecatalogue.service.AppService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MovieDataSource(private val service: AppService, private val coroutineScope: CoroutineScope) :
    PageKeyedDataSource<Int, MovieModel>() {

    val initialState = MutableLiveData<AppResult<Unit>>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {
        coroutineScope.launch {
            try {
                initialState.value = AppResult.loading()
                val initialPage = 1
                val items = service.getMovie(initialPage)
                initialState.value = AppResult.success()
                callback.onResult(items.results, initialPage, items.page + 1)
            } catch (e: Exception) {
                initialState.value = AppResult.error(e.localizedMessage)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        coroutineScope.launch {
            try {
                val items = service.getMovie(params.key)
                callback.onResult(items.results, items.page + 1)
            } catch (e: Exception) {
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {}
}