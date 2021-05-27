package com.rfonzi.mvi_sample.regular.firstPage

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.shared.VerticalStuff

class VerticalStuffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val stuffIdText: TextView by lazy { itemView.findViewById(R.id.stuff_id_text) }
    val stuffDescriptionText: TextView by lazy { itemView.findViewById(R.id.stuff_description_text) }
    val stuffDataText: TextView by lazy { itemView.findViewById(R.id.stuff_data_text) }


    fun bind(verticalStuff: VerticalStuff) {
        stuffIdText.text = verticalStuff.id.toString()
        stuffDescriptionText.text = verticalStuff.description
        stuffDataText.text = verticalStuff.data.toString()
    }
}