package com.rfonzi.mvi_sample.regular.secondPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.domainModels.ColorModel

class ColorAdapter(val onItemSelected: (ColorModel) -> Unit) : ListAdapter<ColorModel, ColorViewHolder>(
    DIFF_CALLBACK
) {

    init {
        submitList(mutableListOf())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemSelected(currentList[position])
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ColorModel>
            get() = object : DiffUtil.ItemCallback<ColorModel>() {
                override fun areItemsTheSame(oldItem: ColorModel, newItem: ColorModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: ColorModel, newItem: ColorModel): Boolean {
                    return oldItem == newItem
                }

            }
    }
}



