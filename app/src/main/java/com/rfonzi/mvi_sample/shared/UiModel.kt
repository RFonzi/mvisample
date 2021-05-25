package com.rfonzi.mvi_sample.shared

import kotlin.random.Random

sealed class MainScreen {
    data class FirstPage(
        val listOfStuff: List<Stuff>
    ) : MainScreen()

    data class SecondPage(
        val chosenThing: Thing,
        val listOfThings: List<Thing>
    ) : MainScreen()
}


data class Stuff(
    val id: Int,
    val description: String,
    val data: Long = Random(id).nextLong()
)

data class Thing(
    val id: Int,
    val description: String,
    val color: String
)