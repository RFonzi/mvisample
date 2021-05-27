package com.rfonzi.mvi_sample.regular.firstPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.uiModels.VerticalDataModel

class VerticalDataAdapter(initialList: List<VerticalDataModel> = listOf()) : ListAdapter<VerticalDataModel, VerticalDataViewHolder>(DIFF_CALLBACK) {

    init {
        submitList(mutableListOf())
        submitList(initialList.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vertical_data, parent, false)
        return VerticalDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<VerticalDataModel>
            get() = object : DiffUtil.ItemCallback<VerticalDataModel>() {
                override fun areItemsTheSame(oldItem: VerticalDataModel, newItem: VerticalDataModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: VerticalDataModel, newItem: VerticalDataModel): Boolean {
                    return oldItem == newItem
                }

            }
    }
}



