package com.assingment.etho.ui.client.adapter

import android.view.ViewGroup
import com.assingment.etho.R
import com.assingment.etho.base.BaseHolder
import com.assingment.etho.base.BaseListAdapter
import com.assingment.etho.connection.Event
import com.assingment.etho.utils.inflate
import javax.inject.Inject

class ClientEventsAdapter @Inject constructor() : BaseListAdapter<Event>(Event.DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Event> {
        return TextEventViewHolder(parent.inflate(R.layout.item_event_text))
    }
}