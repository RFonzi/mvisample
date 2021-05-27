package com.rfonzi.mvi_sample.regular.firstPage

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.uiModels.HorizontalDataModel

class HorizontalStuffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val stuffIdText: TextView by lazy { itemView.findViewById(R.id.horizontal_stuff_id) }
    val stuffDescriptionText: TextView by lazy { itemView.findViewById(R.id.horizontal_stuff_description) }

    fun bind(horizontalDataModel: HorizontalDataModel) {
        stuffIdText.text = horizontalDataModel.id.toString()
        stuffDescriptionText.text = horizontalDataModel.data.toString()
    }
}