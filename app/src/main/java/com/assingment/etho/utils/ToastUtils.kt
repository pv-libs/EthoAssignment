package com.assingment.etho.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CancellationException

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun Context.showToast(error: Throwable, duration: Int = Toast.LENGTH_SHORT) {
    error.printStackTrace()
    if (error is CancellationException) return
    Toast.makeText(this, error.localizedMessage, duration).show()
}