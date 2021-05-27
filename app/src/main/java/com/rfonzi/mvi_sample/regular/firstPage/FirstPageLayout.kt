package com.rfonzi.mvi_sample.regular.firstPage

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.rfonzi.mvi_sample.databinding.LayoutFirstPageBinding
import com.rfonzi.mvi_sample.shared.FirstPage

class FirstPageLayout(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {

    private val binding: LayoutFirstPageBinding =
        LayoutFirstPageBinding.inflate(LayoutInflater.from(context), this)
    private val verticalStuffAdapter = VerticalDataAdapter()
    private val horizontalStuffAdapter = HorizontalStuffAdapter()

    init {
        binding.verticalList.adapter = verticalStuffAdapter
        binding.verticalList.layoutManager = LinearLayoutManager(context)

        binding.horizontalList.adapter = horizontalStuffAdapter
        val horizontalLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalList.layoutManager = horizontalLayoutManager
    }

    fun render(content: FirstPage) {
        when(content) {
            is FirstPage.DataVisible -> renderContent(content)
            is FirstPage.Loading -> renderLoading()
        }
    }

    private fun renderLoading() {
        binding.verticalList.visibility = View.GONE
        binding.horizontalList.visibility = View.GONE
        binding.firstPageLoading.visibility = View.VISIBLE
    }

    private fun renderContent(data: FirstPage.DataVisible) {
        binding.verticalList.visibility = View.VISIBLE
        binding.horizontalList.visibility = View.VISIBLE
        binding.firstPageLoading.visibility = View.GONE

        verticalStuffAdapter.submitList(data.listOfVerticalDataModel)
        horizontalStuffAdapter.submitList(data.listOfHorizontalDatumModels)
    }

}