package com.assingment.etho.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.ReceiveChannel
import timber.log.Timber

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.e(throwable)
}

val applicationScope =
    CoroutineScope(SupervisorJob() + coroutineExceptionHandler + Dispatchers.Main)


suspend inline fun <T> ReceiveChannel<T>.collect(crossinline action: suspend (T) -> Unit) {
    try {
        val iterator = iterator()
        while (iterator.hasNext()) {
            action(iterator.next())
        }
    } catch (e: Exception) {
        e.printStackTrace()
        throw e
    }
}


suspend inline fun ReceiveChannel<Unit>.collect(crossinline action: suspend () -> Unit) {
    collect { _ ->
        action()
    }
}
