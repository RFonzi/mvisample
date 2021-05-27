package com.rfonzi.mvi_sample.shared.domainModels

import kotlin.random.Random

data class DataModel(
    val id: Int,
    val description: String,
    val data: Long = Random(id).nextLong()
)