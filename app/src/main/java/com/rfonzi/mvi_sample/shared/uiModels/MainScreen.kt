package com.rfonzi.mvi_sample.shared.uiModels

import com.rfonzi.mvi_sample.shared.domainModels.ColorModel

sealed interface MainScreen

sealed class FirstPage: MainScreen {
    class Loading() : FirstPage()
    data class DataVisible(
        val listOfVerticalDataModel: List<VerticalDataModel>,
        val listOfHorizontalDatumModels: List<HorizontalDataModel>
    ) : FirstPage()
}

sealed class SecondPage() : MainScreen {
    class Loading() : SecondPage()
    data class ColorsVisible(
        val chosenColorModel: ColorModel?,
        val listOfColorModels: List<ColorModel>
    ) : SecondPage()
}

class InitialLoading() : MainScreen
