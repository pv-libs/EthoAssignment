package com.assingment.etho.data_manager.server.p2p

import com.assingment.etho.connection.CapturePhotoResponseEvent
import com.assingment.etho.connection.Event
import com.assingment.etho.connection.MediaInfoResponseEvent
import com.assingment.etho.connection.RecordVideResponseEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ServerConnectionHelper {

    suspend fun sendRecordVideoEvent()
    suspend fun sendCapturePhotoEvent()
    suspend fun sendGetMediaInfoEvent()
    suspend fun sendNavigationInstructions(instructions: String)

    suspend fun sendPhoneCallEvent(from: String)
    suspend fun sendSmsEvent(from: String, message: String)

    val allReceivedEventsListFlow: StateFlow<List<Event>>

    val recordedVideoFlow: Flow<RecordVideResponseEvent>
    val capturedPhotoFlow: Flow<CapturePhotoResponseEvent>
    val mediaInfoFlow: Flow<MediaInfoResponseEvent>

}