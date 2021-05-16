package com.assingment.etho.connection

import java.io.File
import java.util.*

sealed class Event(
    val id: String = UUID.randomUUID().toString()
) {
    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event) = true
    }
}


sealed class ServerEvent : Event()
sealed class ClientEvent : Event()

// ----- Server Events

class SmsEvent(val fromNumber: String, val message: String) : ServerEvent()

class PhoneCallEvent(val fromNumber: String) : ServerEvent()

class RecordVideoRequestEvent : ServerEvent()

class CapturePhotoRequestEvent : ServerEvent()

class MediaInfoRequestEvent : ServerEvent()

class NavigationInstructionEvent(val instruction: String) : ServerEvent()

// -------------

// Client Events

class RecordVideResponseEvent(
    val requestId: String,
    val file: File,
) : ClientEvent()

class CapturePhotoResponseEvent(
    val requestId: String,
    val file: File,
) : ClientEvent() {

}

class MediaInfoResponseEvent(
    val requestId: String,
    val mediaInfo: List<String>
) : ClientEvent()

// -------------




