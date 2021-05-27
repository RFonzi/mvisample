package com.rfonzi.mvi_sample.regular.firstPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.uiModels.HorizontalDataModel

class HorizontalStuffAdapter(initialList: List<HorizontalDataModel> = listOf()) : ListAdapter<HorizontalDataModel, HorizontalStuffViewHolder>(
    DIFF_CALLBACK
) {

    init {
        submitList(mutableListOf())
        submitList(initialList.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalStuffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_data, parent, false)
        return HorizontalStuffViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalStuffViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HorizontalDataModel>
            get() = object : DiffUtil.ItemCallback<HorizontalDataModel>() {
                override fun areItemsTheSame(oldItem: HorizontalDataModel, newItem: HorizontalDataModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: HorizontalDataModel, newItem: HorizontalDataModel): Boolean {
                    return oldItem == newItem
                }

            }
    }
}



