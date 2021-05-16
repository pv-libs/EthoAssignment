package com.assingment.etho.ui.server

import com.assingment.etho.base.BaseViewModel
import com.assingment.etho.data_manager.server.ServerDataManager
import com.assingment.etho.utils.safeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class ServerHomeViewModel @Inject constructor(
    private val dataManager: ServerDataManager,
) : BaseViewModel() {

    val receivedEvents = dataManager.allReceivedEventsListFlow
    val openNavigationDialogEvent = Channel<Unit>(Channel.CONFLATED)

    fun sendRecordVideoRequest() {
        safeLaunch {
            dataManager.sendRecordVideoEvent()
            showToast("Event Sent")
        }
    }

    fun sendCaptureImageRequest() {
        safeLaunch {
            dataManager.sendCapturePhotoEvent()
            showToast("Event Sent")
        }
    }

    fun sendMediaInfoRequest() {
        safeLaunch {
            dataManager.sendGetMediaInfoEvent()
            showToast("Event Sent")
        }
    }

    fun sendNavigationInstructions(instruction: String) {
        if (instruction.isBlank()) return
        safeLaunch {
            dataManager.sendNavigationInstructions(instruction)
            showToast("Event Sent")
        }
    }

    fun openNavigationInstructionsDialog() {
        openNavigationDialogEvent.offer(Unit)
    }

}