package com.assingment.etho.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


/**
 * Base List Adapter for all the Adapter in applications
 *
 * @param <ViewHolder>
 * @param <Item>
</Item></ViewHolder> */
abstract class BaseListAdapter<Item>(
    diffUtils: DiffUtil.ItemCallback<Item>,
) : ListAdapter<Item, BaseHolder<Item>>(diffUtils) {

    override fun onBindViewHolder(viewHolder: BaseHolder<Item>, position: Int) {
        val item = getItem(position)
        if (item != null) {
            viewHolder.onBind(item)
        }
    }

    override fun onViewRecycled(holder: BaseHolder<Item>) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

}
