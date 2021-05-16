package com.assingment.etho.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Binding : ViewDataBinding, Item>(val binding: Binding) :
    BaseHolder<Item>(binding.root)

abstract class BaseHolder<Item>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(item: Item)

    open fun onRecycled() {
        // override
    }
}
