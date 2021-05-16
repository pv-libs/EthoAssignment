package com.assingment.etho.utils


import com.assingment.etho.connection.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

inline fun <reified T : Event> Flow<Event>.mapToType(): Flow<T> {
    return transformLatest {
        if (it is T) {
            emit(it)
        }
    }
}


/**
 * Collecting all events to a list
 * */
fun Flow<Event>.storeAsList(): StateFlow<List<Event>> = flow {
    Timber.d("storeAsList")
    var eventsList = listOf<Event>()
    emit(eventsList)
    collect {
        eventsList = eventsList.toMutableList().apply {
            add(it)
        }
        emit(eventsList)
    }
}.flowOn(Dispatchers.Default)
    .stateIn(applicationScope, SharingStarted.Eagerly, listOf())