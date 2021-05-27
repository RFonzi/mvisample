package com.rfonzi.mvi_sample.shared

import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UiPresenter @Inject constructor(
    private val stuffInteractor: StuffInteractor
) {

    private val mutableModel: MutableStateFlow<MainScreen> = MutableStateFlow(InitialLoading())
    val model: StateFlow<MainScreen> = mutableModel

    suspend fun sendIntent(intent: MainIntent) {
        when(intent) {
            is LoadFirstPageIntent -> loadFirstPage()
        }
    }

    suspend fun loadFirstPage(){
        val content = stuffInteractor.getStuff()
        val model = FirstPage.ContentVisible(
            listOfHorizontalStuff = content.map { it.asHorizontalStuff() },
            listOfVerticalStuff = content.map { it.asVerticalStuff() }
        )
        mutableModel.emit(model)
    }

    private fun Stuff.asVerticalStuff() : VerticalStuff {
        return VerticalStuff(
            id,
            description,
            data
        )
    }

    private fun Stuff.asHorizontalStuff() : HorizontalStuff {
        return HorizontalStuff(
            id,
            data
        )
    }
}