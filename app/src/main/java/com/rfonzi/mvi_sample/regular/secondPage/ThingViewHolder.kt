package com.rfonzi.mvi_sample.regular.secondPage

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.databinding.ItemThingBinding
import com.rfonzi.mvi_sample.shared.HorizontalStuff
import com.rfonzi.mvi_sample.shared.Thing

class ThingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ItemThingBinding.bind(itemView)

    fun bind(thing: Thing) {
        binding.colorBg.setBackgroundColor(Color.parseColor(thing.color))
        binding.colorText.text = thing.description
    }
}