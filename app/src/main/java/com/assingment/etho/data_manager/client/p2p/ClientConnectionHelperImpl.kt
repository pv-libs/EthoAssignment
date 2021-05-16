package com.assingment.etho.data_manager.client.p2p

import com.assingment.etho.connection.*
import com.assingment.etho.utils.mapToType
import com.assingment.etho.utils.storeAsList
import java.io.File
import javax.inject.Inject

class ClientConnectionHelperImpl @Inject constructor(
    @ClientConnection private val connection: Connection
) : ClientConnectionHelper {

    private val eventsFlow = connection.eventFlow

    /**
     * collecting all the events to a list
     * */
    override val allReceivedEventsListFlow = eventsFlow.storeAsList()

    override val smsEventFlow = eventsFlow.mapToType<SmsEvent>()
    override val phoneCallEventFlow = eventsFlow.mapToType<PhoneCallEvent>()
    override val recordVideoEventFlow = eventsFlow.mapToType<RecordVideoRequestEvent>()
    override val capturePhotoEventFlow = eventsFlow.mapToType<CapturePhotoRequestEvent>()
    override val getMediaInfoEventFlow = eventsFlow.mapToType<MediaInfoRequestEvent>()
    override val navigationInstructionsEventFlow =
        eventsFlow.mapToType<NavigationInstructionEvent>()

    override suspend fun sendRecordedVideo(requestId: String, file: File) {
        connection.sendEvent(RecordVideResponseEvent(requestId, file))
    }

    override suspend fun sendCapturedImage(requestId: String, file: File) {
        connection.sendEvent(CapturePhotoResponseEvent(requestId, file))
    }

    override suspend fun sendMediaInfo(requestId: String, mediaInfo: List<String>) {
        connection.sendEvent(MediaInfoResponseEvent(requestId, mediaInfo))
    }

}