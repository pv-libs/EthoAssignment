package com.assingment.etho.ui.client.adapter

import android.annotation.SuppressLint
import com.assingment.etho.base.BaseViewHolder
import com.assingment.etho.connection.*
import com.assingment.etho.databinding.ItemEventMediaBinding
import com.assingment.etho.databinding.ItemEventTextBinding
import com.bumptech.glide.Glide

class MediaEventViewHolder(binding: ItemEventMediaBinding) :
    BaseViewHolder<ItemEventMediaBinding, Event>(binding) {
    override fun onBind(item: Event) {
        when (item) {
            is CapturePhotoResponseEvent -> {
                Glide.with(binding.imageView)
                    .load(item.file)
                    .into(binding.imageView)
                binding.eventTypeView.text = "Capture Photo Response"
            }
            is RecordVideResponseEvent -> {
                Glide.with(binding.imageView)
                    .load(item.file)
                    .into(binding.imageView)
                binding.eventTypeView.text = "Record Video Response"
            }
            else -> throw IllegalStateException()
        }
    }
}


class TextEventViewHolder(binding: ItemEventTextBinding) :
    BaseViewHolder<ItemEventTextBinding, Event>(binding) {
    @SuppressLint("SetTextI18n")
    override fun onBind(item: Event) {
        when (item) {
            is SmsEvent -> {
                binding.eventTypeView.text = "SMS"
                binding.textView.text = "From : ${item.fromNumber}\nmessage : ${item.message}"
            }
            is PhoneCallEvent -> {
                binding.eventTypeView.text = "Phone Call"
                binding.textView.text = "From : ${item.fromNumber}"
            }
            is NavigationInstructionEvent -> {
                binding.eventTypeView.text = "Navigation Instructions"
                binding.textView.text = item.instruction
            }

            is CapturePhotoRequestEvent -> {
                binding.textView.text = ""
                binding.eventTypeView.text = "Capture Photo Request"
            }
            is CapturePhotoResponseEvent -> {
                binding.textView.text = ""
                binding.eventTypeView.text = "Capture Photo"
            }

            is MediaInfoRequestEvent -> {
                binding.textView.text = ""
                binding.eventTypeView.text = "Media Info Request"
            }
            is MediaInfoResponseEvent -> {
                binding.textView.text = item.mediaInfo.joinToString("\n")
                binding.eventTypeView.text = "Media Info"
            }

            is RecordVideResponseEvent -> {
                binding.textView.text = ""
                binding.eventTypeView.text = "Record Video"
            }
            is RecordVideoRequestEvent -> {
                binding.textView.text = ""
                binding.eventTypeView.text = "Record Video Request"
            }
        }
    }
}

