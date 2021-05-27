package com.rfonzi.mvi_sample.shared

import kotlin.random.Random

data class Stuff(
    val id: Int,
    val description: String,
    val data: Long = Random(id).nextLong()
)