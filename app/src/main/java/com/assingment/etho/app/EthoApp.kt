package com.assingment.etho.app

import android.app.Application
import com.assingment.etho.utils.MyPhoneStateListener
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
class EthoApp : Application() {

    @Inject
    lateinit var phoneStateListener: MyPhoneStateListener

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }


}