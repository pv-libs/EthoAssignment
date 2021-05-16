package com.assingment.etho.data_manager.server.p2p

import com.assingment.etho.connection.*
import com.assingment.etho.utils.mapToType
import com.assingment.etho.utils.storeAsList
import javax.inject.Inject

class ServerConnectionHelperImpl @Inject constructor(
    @ServerConnection private val connection: Connection
) : ServerConnectionHelper {

    private val eventsFlow = connection.eventFlow

    override val allReceivedEventsListFlow = eventsFlow.storeAsList()
    override val recordedVideoFlow = eventsFlow.mapToType<RecordVideResponseEvent>()
    override val capturedPhotoFlow = eventsFlow.mapToType<CapturePhotoResponseEvent>()
    override val mediaInfoFlow = eventsFlow.mapToType<MediaInfoResponseEvent>()

    override suspend fun sendRecordVideoEvent() {
        connection.sendEvent(RecordVideoRequestEvent())
    }

    override suspend fun sendCapturePhotoEvent() {
        connection.sendEvent(CapturePhotoRequestEvent())
    }

    override suspend fun sendGetMediaInfoEvent() {
        connection.sendEvent(MediaInfoRequestEvent())
    }

    override suspend fun sendNavigationInstructions(instructions: String) {
        connection.sendEvent(NavigationInstructionEvent(instructions))
    }

    override suspend fun sendPhoneCallEvent(from: String) {
        connection.sendEvent(PhoneCallEvent(from))
    }

    override suspend fun sendSmsEvent(from: String, message: String) {
        connection.sendEvent(SmsEvent(from, message))
    }

}