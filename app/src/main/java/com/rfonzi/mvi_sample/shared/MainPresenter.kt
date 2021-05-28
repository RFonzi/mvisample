package com.rfonzi.mvi_sample.shared

import com.rfonzi.mvi_sample.shared.domainInteractors.ColorInteractor
import com.rfonzi.mvi_sample.shared.domainInteractors.DataInteractor
import com.rfonzi.mvi_sample.shared.domainModels.DataModel
import com.rfonzi.mvi_sample.shared.domainModels.ColorModel
import com.rfonzi.mvi_sample.shared.uiIntents.LoadFirstPageIntent
import com.rfonzi.mvi_sample.shared.uiIntents.LoadSecondPageIntent
import com.rfonzi.mvi_sample.shared.uiIntents.MainIntent
import com.rfonzi.mvi_sample.shared.uiIntents.SelectThingIntent
import com.rfonzi.mvi_sample.shared.uiModels.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val dataInteractor: DataInteractor,
    private val colorInteractor: ColorInteractor
) {

    private val mutableModel: MutableStateFlow<MainScreen> = MutableStateFlow(InitialLoading())
    val model: Flow<MainScreen> = mutableModel

    suspend fun sendIntent(intent: MainIntent) {
        when(intent) {
            is LoadFirstPageIntent -> loadFirstPage()
            is LoadSecondPageIntent -> loadSecondPage()
            is SelectThingIntent -> updateSelectedThing(intent.selectedColorModel)
        }
    }

    suspend fun loadFirstPage(){
        mutableModel.emit(FirstPage.Loading())
        val content = dataInteractor.getData()
        val model = FirstPage.DataVisible(
            listOfHorizontalDataModels = content.map { it.asHorizontalData() },
            listOfVerticalDataModels = content.map { it.asVerticalData() }
        )
        mutableModel.emit(model)
    }

    suspend fun loadSecondPage() {
        mutableModel.emit(SecondPage.Loading())
        val things = colorInteractor.getThings()
        val model = SecondPage.ColorsVisible(
            chosenColorModel = null,
            things
        )
        mutableModel.emit(model)
    }

    suspend fun updateSelectedThing(newColorModel: ColorModel) {
        // Replace line below with actual state machine impl
        val screen = mutableModel.value as? SecondPage.ColorsVisible ?: return

        mutableModel.emit(
            screen.copy(
                chosenColorModel = newColorModel
            )
        )
    }

    private fun DataModel.asVerticalData() : VerticalDataModel {
        return VerticalDataModel(
            id,
            description,
            data
        )
    }

    private fun DataModel.asHorizontalData() : HorizontalDataModel {
        return HorizontalDataModel(
            id,
            data
        )
    }
}