package com.rfonzi.mvi_sample.regular.secondPage

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.rfonzi.mvi_sample.databinding.LayoutSecondPageBinding
import com.rfonzi.mvi_sample.shared.uiIntents.MainIntent
import com.rfonzi.mvi_sample.shared.uiModels.SecondPage
import com.rfonzi.mvi_sample.shared.uiIntents.SelectThingIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class SecondPageLayout(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet) {

    private val binding = LayoutSecondPageBinding.inflate(LayoutInflater.from(context), this)
    private val colorAdapter = ColorAdapter {
        mutableIntents.tryEmit(
            SelectThingIntent(it)
        )
    }

    init {
        binding.colorListView.adapter = colorAdapter
        binding.colorListView.layoutManager = GridLayoutManager(context, 4)


    }

    fun render(screen: SecondPage) {
        when(screen) {
            is SecondPage.Loading -> renderLoading()
            is SecondPage.ColorsVisible -> renderThings(screen)
        }
    }

    private val mutableIntents: MutableStateFlow<MainIntent?> = MutableStateFlow(null)
    val intents: Flow<MainIntent> = mutableIntents.filterNotNull()

    private fun renderLoading() {
        binding.colorListView.visibility = View.GONE
        binding.selectedColorCard.visibility = View.GONE
        binding.secondPageLoading.visibility = View.VISIBLE
    }

    private fun renderThings(screen: SecondPage.ColorsVisible) {
        binding.colorListView.visibility = View.VISIBLE
        binding.selectedColorCard.visibility = View.VISIBLE
        binding.secondPageLoading.visibility = View.GONE

        screen.chosenColorModel?.color?.let { colorString ->
            val animator = ValueAnimator.ofArgb(binding.selectedColorCard.cardBackgroundColor.defaultColor, colorString.toInt())
            animator.setEvaluator(ArgbEvaluator())
            animator.addUpdateListener {
                binding.selectedColorCard.setCardBackgroundColor(it.animatedValue as Int)
            }
            animator.start()
        }

        colorAdapter.submitList(screen.listOfColorModels)
    }
}