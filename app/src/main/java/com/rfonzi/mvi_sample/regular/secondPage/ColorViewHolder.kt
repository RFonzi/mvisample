package com.rfonzi.mvi_sample.regular.secondPage

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.databinding.ItemColorBinding
import com.rfonzi.mvi_sample.shared.domainModels.ColorModel

class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemColorBinding.bind(itemView)

    fun bind(colorModel: ColorModel) {
        binding.colorBg.setBackgroundColor(colorModel.color.toInt())
        binding.colorText.text = colorModel.description
    }
}