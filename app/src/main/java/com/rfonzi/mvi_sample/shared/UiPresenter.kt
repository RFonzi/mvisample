package com.rfonzi.mvi_sample.shared

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UiPresenter {

    private val sampleScreen = MainScreen.FirstPage(
        (1..1000).map {
            Stuff(
                it,
                "Stuff #$it"
            )
        }
    )

    val model: StateFlow<MainScreen> = MutableStateFlow(sampleScreen)

}