package com.assingment.etho.data_manager.client.p2p

import com.assingment.etho.connection.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

interface ClientConnectionHelper {

    val allReceivedEventsListFlow: StateFlow<List<Event>>

    val recordVideoEventFlow: Flow<RecordVideoRequestEvent>
    val capturePhotoEventFlow: Flow<CapturePhotoRequestEvent>
    val getMediaInfoEventFlow: Flow<MediaInfoRequestEvent>
    val navigationInstructionsEventFlow: Flow<NavigationInstructionEvent>
    val smsEventFlow: Flow<SmsEvent>
    val phoneCallEventFlow: Flow<PhoneCallEvent>

    suspend fun sendRecordedVideo(requestId: String, file: File)

    suspend fun sendCapturedImage(requestId: String, file: File)

    suspend fun sendMediaInfo(requestId: String, mediaInfo: List<String>)

}