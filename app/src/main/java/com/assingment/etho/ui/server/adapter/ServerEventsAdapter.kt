package com.assingment.etho.ui.server.adapter

import android.view.ViewGroup
import com.assingment.etho.R
import com.assingment.etho.base.BaseHolder
import com.assingment.etho.base.BaseListAdapter
import com.assingment.etho.connection.CapturePhotoResponseEvent
import com.assingment.etho.connection.Event
import com.assingment.etho.connection.RecordVideResponseEvent
import com.assingment.etho.ui.client.adapter.MediaEventViewHolder
import com.assingment.etho.ui.client.adapter.TextEventViewHolder
import com.assingment.etho.utils.inflate
import javax.inject.Inject

class ServerEventsAdapter @Inject constructor() : BaseListAdapter<Event>(Event.DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Event> {
        return when (viewType) {
            VIEW_TYPE_MEDIA -> MediaEventViewHolder(parent.inflate(R.layout.item_event_media))
            else -> TextEventViewHolder(parent.inflate(R.layout.item_event_text))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CapturePhotoResponseEvent, is RecordVideResponseEvent -> VIEW_TYPE_MEDIA
            else -> VIEW_TYPE_TEXT
        }
    }

    companion object {
        const val VIEW_TYPE_TEXT = 0
        const val VIEW_TYPE_MEDIA = 1
    }

}