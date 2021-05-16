package com.assingment.etho.connection

import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Qualifier


interface Connection {
    /**
     * [SharedFlow] of received events
     * */
    val eventFlow: SharedFlow<Event>

    /**
     * function to send events
     * */
    suspend fun sendEvent(event: Event)
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServerConnection

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClientConnection
