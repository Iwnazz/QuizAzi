package com.example.quizzazi.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector


sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>(), Flow<Response<Boolean>> {
        override suspend fun collect(collector: FlowCollector<Response<Boolean>>) {
            TODO("Not yet implemented")
        }
    }

    data class Error(val message: String, val throwable: Throwable? = null) : Response<Nothing>(),
        Flow<Response<Boolean>> {
        override suspend fun collect(collector: FlowCollector<Response<Boolean>>) {
            TODO("Not yet implemented")
        }
    }

    object Loading : Response<Nothing>()

    companion object {
        fun <T> success(data: T): Response<T> = Success(data)
        fun error(message: String, throwable: Throwable? = null): Response<Nothing> =
            Error(message, throwable)

        fun loading(): Response<Nothing> = Loading
    }
}