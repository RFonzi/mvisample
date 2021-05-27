package com.rfonzi.mvi_sample.shared

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun uiPresenter(stuffInteractor: StuffInteractor, thingInteractor: ThingInteractor): UiPresenter {
        return UiPresenter(stuffInteractor, thingInteractor)
    }

    @Provides
    fun stuffInteractor(): StuffInteractor {
        return StuffInteractor()
    }

    @Provides
    fun thingInteractor(): ThingInteractor {
        return ThingInteractor()
    }
}