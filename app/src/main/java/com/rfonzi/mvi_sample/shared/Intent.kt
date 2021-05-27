package com.rfonzi.mvi_sample.shared

sealed interface MainIntent

class LoadFirstPageIntent() : MainIntent

class LoadSecondPageIntent() : MainIntent

data class SelectThingIntent(val selectedThing: Thing): MainIntent