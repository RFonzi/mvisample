package com.rfonzi.mvi_sample.shared

sealed interface MainIntent

class LoadFirstPageIntent() : MainIntent