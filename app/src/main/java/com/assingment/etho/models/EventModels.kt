package com.assingment.etho.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SmsInfo(
    val from: String,
    val data: String,
) : Parcelable

@Parcelize
data class CallInfo(
    val from: String,
) : Parcelable

@Parcelize
data class NavigationInstructionsInfo(
    val data: String,
) : Parcelable




