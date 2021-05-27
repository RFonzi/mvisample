package com.rfonzi.mvi_sample.shared

import com.rfonzi.mvi_sample.shared.domainModels.ColorModel

sealed interface MainIntent

class LoadFirstPageIntent() : MainIntent

class LoadSecondPageIntent() : MainIntent

data class SelectThingIntent(val selectedColorModel: ColorModel): MainIntent