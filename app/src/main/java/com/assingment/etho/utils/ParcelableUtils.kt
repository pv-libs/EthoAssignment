package com.assingment.etho.utils

import android.os.Parcel
import android.os.Parcelable

fun Parcelable.toBytes(): ByteArray {
    val parcel = Parcel.obtain()
    this.writeToParcel(parcel, 0)
    val byteArray = parcel.marshall()
    parcel.recycle()
    return byteArray
}

fun <T> ByteArray.toObject(creator: Parcelable.Creator<T>): T {
    val parcel = Parcel.obtain()
    parcel.unmarshall(this, 0, size)
    parcel.setDataPosition(0)
    val result = creator.createFromParcel(parcel)
    parcel.recycle()
    return result
}