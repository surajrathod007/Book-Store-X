package com.surajrathod.bookstore.networkbound

import com.surajrathod.bookstore.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.lang.Exception


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    emit(Result.Loading())
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        //emit(Result.Loading())
        try {
            saveFetchResult(fetch())
            query().map {
                Result.Success(it)
            }
        } catch (e: Exception) {
            Result.Failure<ResultType>("NB Exception"+e.message, e)
            kotlinx.coroutines.delay(500)
            query().map {
                Result.Success(it)
            }
        }
    } else {
        query().map {
            Result.Success(it)
        }
    }

    emitAll(flow)
}