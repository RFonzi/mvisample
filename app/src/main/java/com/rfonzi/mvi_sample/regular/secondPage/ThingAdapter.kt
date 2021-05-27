package com.rfonzi.mvi_sample.regular.secondPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.Thing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ThingAdapter(val onItemSelected: (Thing) -> Unit) : ListAdapter<Thing, ThingViewHolder>(
    DIFF_CALLBACK
) {

    init {
        submitList(mutableListOf())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_thing, parent, false)
        return ThingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemSelected(currentList[position])
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Thing>
            get() = object : DiffUtil.ItemCallback<Thing>() {
                override fun areItemsTheSame(oldItem: Thing, newItem: Thing): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Thing, newItem: Thing): Boolean {
                    return oldItem == newItem
                }

            }
    }
}



