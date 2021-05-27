package com.rfonzi.mvi_sample.regular.firstPage

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.databinding.ItemVerticalDataBinding
import com.rfonzi.mvi_sample.shared.uiModels.VerticalDataModel

class VerticalDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemVerticalDataBinding.bind(itemView)

    fun bind(verticalDataModel: VerticalDataModel) {
        binding.dataId.text = verticalDataModel.id.toString()
        binding.dataDescriptionText.text = verticalDataModel.description
        binding.dataContents.text = verticalDataModel.data.toString()
    }
}