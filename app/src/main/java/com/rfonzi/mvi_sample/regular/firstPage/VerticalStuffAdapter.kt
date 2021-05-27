package com.rfonzi.mvi_sample.regular.firstPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.VerticalStuff

class VerticalStuffAdapter(initialList: List<VerticalStuff> = listOf()) : ListAdapter<VerticalStuff, VerticalStuffViewHolder>(DIFF_CALLBACK) {

    init {
        submitList(mutableListOf())
        submitList(initialList.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalStuffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_stuff, parent, false)
        return VerticalStuffViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalStuffViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<VerticalStuff>
            get() = object : DiffUtil.ItemCallback<VerticalStuff>() {
                override fun areItemsTheSame(oldItem: VerticalStuff, newItem: VerticalStuff): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: VerticalStuff, newItem: VerticalStuff): Boolean {
                    return oldItem == newItem
                }

            }
    }
}



