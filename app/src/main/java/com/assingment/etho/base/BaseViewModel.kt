package com.assingment.etho.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel

open class BaseViewModel : ViewModel() {

    val showToastEvent = Channel<String>(Channel.CONFLATED)

    fun showToast(message: String) {
        showToastEvent.offer(message)
    }

}