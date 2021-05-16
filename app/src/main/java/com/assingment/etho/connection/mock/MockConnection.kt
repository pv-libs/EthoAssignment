package com.assingment.etho.connection.mock

import com.assingment.etho.connection.Connection
import com.assingment.etho.connection.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockConnection @Inject constructor() {

    private val serverEventFlow = MutableSharedFlow<Event>()
    private val clientEventFlow = MutableSharedFlow<Event>()

    fun getServerConnection(): Connection = object : Connection {
        override val eventFlow: SharedFlow<Event> = serverEventFlow
        override suspend fun sendEvent(event: Event) {
            // can convert event object to bytes and transmit
            // after receiving re-construct event object and send it to clientEventFlow
            clientEventFlow.emit(event)
        }
    }

    fun getClientConnection(): Connection = object : Connection {
        override val eventFlow: SharedFlow<Event> = clientEventFlow
        override suspend fun sendEvent(event: Event) {
            serverEventFlow.emit(event)
        }
    }

}

