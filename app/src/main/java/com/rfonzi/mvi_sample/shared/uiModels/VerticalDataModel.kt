package com.rfonzi.mvi_sample.shared.uiModels

import kotlin.random.Random

data class VerticalDataModel(
    val id: Int,
    val description: String,
    val data: Long = Random(id).nextLong()
)