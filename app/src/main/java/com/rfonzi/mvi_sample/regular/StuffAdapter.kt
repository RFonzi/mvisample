package com.rfonzi.mvi_sample.regular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.Stuff

class StuffAdapter(initialList: List<Stuff> = listOf()) : ListAdapter<Stuff, StuffViewHolder>(DIFF_CALLBACK) {

    init {
        submitList(mutableListOf())
        submitList(initialList.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StuffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stuff, parent, false)
        return StuffViewHolder(view)
    }

    override fun onBindViewHolder(holder: StuffViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



val DIFF_CALLBACK: DiffUtil.ItemCallback<Stuff>
    get() = object : DiffUtil.ItemCallback<Stuff>() {
        override fun areItemsTheSame(oldItem: Stuff, newItem: Stuff): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Stuff, newItem: Stuff): Boolean {
            return oldItem == newItem
        }

    }