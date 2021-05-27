package com.rfonzi.mvi_sample.shared.uiModels

import kotlin.random.Random

data class HorizontalDataModel(
    val id: Int,
    val data: Long = Random(id).nextLong()
)