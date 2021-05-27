package com.rfonzi.mvi_sample.regular.firstPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.HorizontalStuff

class HorizontalStuffAdapter(initialList: List<HorizontalStuff> = listOf()) : ListAdapter<HorizontalStuff, HorizontalStuffViewHolder>(
    DIFF_CALLBACK
) {

    init {
        submitList(mutableListOf())
        submitList(initialList.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalStuffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_stuff, parent, false)
        return HorizontalStuffViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalStuffViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HorizontalStuff>
            get() = object : DiffUtil.ItemCallback<HorizontalStuff>() {
                override fun areItemsTheSame(oldItem: HorizontalStuff, newItem: HorizontalStuff): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: HorizontalStuff, newItem: HorizontalStuff): Boolean {
                    return oldItem == newItem
                }

            }
    }
}



