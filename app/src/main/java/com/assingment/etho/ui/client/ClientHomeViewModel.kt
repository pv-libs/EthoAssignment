package com.assingment.etho.ui.client


import android.app.Application
import com.assingment.etho.R
import com.assingment.etho.base.BaseViewModel
import com.assingment.etho.connection.CapturePhotoRequestEvent
import com.assingment.etho.connection.MediaInfoRequestEvent
import com.assingment.etho.connection.RecordVideoRequestEvent
import com.assingment.etho.data_manager.client.ClientDataManager
import com.assingment.etho.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject


@HiltViewModel
class ClientHomeViewModel @Inject constructor(
    private val application: Application,
    private val dataManager: ClientDataManager,
) : BaseViewModel() {

    val receivedEvents = dataManager.allReceivedEventsListFlow

    init {
        safeLaunch {
            dataManager.capturePhotoEventFlow.collect(::sendCapturePhotoEvent)
        }
        safeLaunch {
            dataManager.recordVideoEventFlow.collect(::sendRecordedVideoEvent)
        }
        safeLaunch {
            dataManager.getMediaInfoEventFlow.collect(::sendMediaInfoEvent)
        }
    }

    private fun sendCapturePhotoEvent(capturePhotoRequestEvent: CapturePhotoRequestEvent) {
        safeLaunch {
            dataManager.sendCapturedImage(capturePhotoRequestEvent.id, getSampleImage())
        }
    }

    private fun sendRecordedVideoEvent(recordVideoRequestEvent: RecordVideoRequestEvent) {
        safeLaunch {
            dataManager.sendCapturedImage(recordVideoRequestEvent.id, getSampleVideo())
        }
    }

    private fun sendMediaInfoEvent(mediaInfoRequestEvent: MediaInfoRequestEvent) {
        safeLaunch {
            val mediaInfo = listOf("temp1.jpg", "temp2.jpg", "temp3.jpg")
            dataManager.sendMediaInfo(mediaInfoRequestEvent.id, mediaInfo)
        }
    }

    private suspend fun getSampleImage(): File = withContext(Dispatchers.IO) {
        val imageFile = File(application.cacheDir, "sample_image.webp")
        if (!imageFile.exists()) {
            imageFile.parentFile?.mkdirs()
            application.resources.openRawResource(R.raw.ic_launcher)
                .copyTo(imageFile.outputStream())
        }
        imageFile
    }


    private suspend fun getSampleVideo(): File = withContext(Dispatchers.IO) {
        val videoFile = File(application.cacheDir, "sample_video.mp4")
        if (!videoFile.exists()) {
            videoFile.parentFile?.mkdirs()
            application.resources.openRawResource(R.raw.sample_video)
                .copyTo(videoFile.outputStream())
        }
        videoFile
    }
}