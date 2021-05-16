package com.assingment.etho.utils

import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import com.assingment.etho.data_manager.server.ServerDataManager
import timber.log.Timber
import javax.inject.Inject

class MyPhoneStateListener @Inject constructor(
    private val dataManager: ServerDataManager,
) : PhoneStateListener() {
    private var previousState = TelephonyManager.CALL_STATE_IDLE
    override fun onCallStateChanged(state: Int, phoneNumber: String?) {
        super.onCallStateChanged(state, phoneNumber)
        if (state == TelephonyManager.CALL_STATE_RINGING && state != previousState) {
            triggerEvent(phoneNumber)
        }
        Timber.d("Phone Number : $phoneNumber")
        previousState = state
    }

    private fun triggerEvent(phoneNumber: String?) {
        applicationScope.safeLaunch {
            dataManager.sendPhoneCallEvent(phoneNumber ?: return@safeLaunch)
        }
    }
}