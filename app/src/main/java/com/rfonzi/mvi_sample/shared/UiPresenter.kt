package com.rfonzi.mvi_sample.shared

import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UiPresenter @Inject constructor(
    private val stuffInteractor: StuffInteractor,
    private val thingInteractor: ThingInteractor
) {

    private val mutableModel: MutableStateFlow<MainScreen> = MutableStateFlow(InitialLoading())
    val model: Flow<MainScreen> = mutableModel

    suspend fun sendIntent(intent: MainIntent) {
        when(intent) {
            is LoadFirstPageIntent -> loadFirstPage()
            is LoadSecondPageIntent -> loadSecondPage()
            is SelectThingIntent -> updateSelectedThing(intent.selectedThing)
        }
    }

    suspend fun loadFirstPage(){
        mutableModel.emit(FirstPage.Loading())
        val content = stuffInteractor.getStuff()
        val model = FirstPage.ContentVisible(
            listOfHorizontalStuff = content.map { it.asHorizontalStuff() },
            listOfVerticalStuff = content.map { it.asVerticalStuff() }
        )
        mutableModel.emit(model)
    }

    suspend fun loadSecondPage() {
        mutableModel.emit(SecondPage.Loading())
        val things = thingInteractor.getThings()
        val model = SecondPage.ThingsVisible(
            chosenThing = null,
            things
        )
        mutableModel.emit(model)
    }

    suspend fun updateSelectedThing(newThing: Thing) {
        // Replace line below with actual state machine impl
        val screen = mutableModel.value as? SecondPage.ThingsVisible ?: return

        mutableModel.emit(
            screen.copy(
                chosenThing = newThing
            )
        )
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