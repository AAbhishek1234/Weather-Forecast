package com.aabhishek.weatherforecast.util

import com.aabhishek.weatherforecast.business.data.cache.CacheResult
import com.aabhishek.weatherforecast.business.data.network.ApiResult
import com.aabhishek.weatherforecast.util.Constants.Companion.CACHE_ERROR_TIMEOUT
import com.aabhishek.weatherforecast.util.Constants.Companion.CACHE_ERROR_UNKNOWN
import com.aabhishek.weatherforecast.util.Constants.Companion.NETWORK_ERROR_TIMEOUT
import com.aabhishek.weatherforecast.util.Constants.Companion.NETWORK_ERROR_UNKNOWN
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            ApiResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    ApiResult.GenericError(code, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    ApiResult.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = throwable.toString()
                    ApiResult.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    ApiResult.GenericError(
                        null,
                        NETWORK_ERROR_UNKNOWN
                    )
                }
            }
        }
    }
}

suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    cacheCall: suspend () -> T?
): CacheResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            CacheResult.Success(cacheCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {

                is TimeoutCancellationException -> {
                    CacheResult.GenericError(CACHE_ERROR_TIMEOUT)
                }
                else -> {
                    CacheResult.GenericError(CACHE_ERROR_UNKNOWN)
                }
            }
        }
    }
}