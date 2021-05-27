package com.rfonzi.mvi_sample.shared

import kotlin.random.Random

sealed interface MainScreen

sealed class FirstPage: MainScreen {
    class Loading() : FirstPage()
    data class ContentVisible(
        val listOfVerticalStuff: List<VerticalStuff>,
        val listOfHorizontalStuff: List<HorizontalStuff>
    ) : FirstPage()
}

sealed class SecondPage() : MainScreen {
    class Loading() : SecondPage()
    data class ThingsVisible(
        val chosenThing: Thing?,
        val listOfThings: List<Thing>
    ) : SecondPage()
}

class InitialLoading() : MainScreen


data class VerticalStuff(
    val id: Int,
    val description: String,
    val data: Long = Random(id).nextLong()
)

data class HorizontalStuff(
    val id: Int,
    val data: Long = Random(id).nextLong()
)

data class Thing(
    val id: Int,
    val description: String,
    val color: String
)