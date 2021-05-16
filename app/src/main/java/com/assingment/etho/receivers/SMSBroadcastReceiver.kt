package com.assingment.etho.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import com.assingment.etho.data_manager.server.ServerDataManager
import com.assingment.etho.utils.applicationScope
import com.assingment.etho.utils.safeLaunch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SMSBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var serverDataManager: ServerDataManager

    override fun onReceive(context: Context, intent: Intent) {
        val pdus = (intent.extras?.get("pdus") ?: return) as Array<*>

        val messages: Array<SmsMessage?> = arrayOfNulls(pdus.size)
        for (i in pdus.indices) {
            messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
            applicationScope.safeLaunch {
                messages.forEach {
                    it ?: return@forEach
                    serverDataManager.sendSmsEvent(
                        it.originatingAddress ?: return@forEach,
                        it.displayMessageBody
                    )
                }
            }
        }
    }
}