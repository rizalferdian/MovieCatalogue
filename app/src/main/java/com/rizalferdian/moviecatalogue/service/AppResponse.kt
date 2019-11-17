package com.rizalferdian.moviecatalogue.service

import com.google.gson.annotations.SerializedName

data class AppResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: T
)

enum class ApiStatus {
    SUCCESS,
    ERROR,
    LOADING
}

data class AppResult<out T> private constructor(
    val status: ApiStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T? = null): AppResult<T> {
            return AppResult(
                ApiStatus.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String?, data: T? = null): AppResult<T> {
            return AppResult(
                ApiStatus.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T? = null): AppResult<T> {
            return AppResult(
                ApiStatus.LOADING,
                data,
                null
            )
        }
    }
}