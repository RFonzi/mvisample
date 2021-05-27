package com.rfonzi.mvi_sample.shared

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun uiPresenter(dataInteractor: DataInteractor, colorInteractor: ColorInteractor): MainPresenter {
        return MainPresenter(dataInteractor, colorInteractor)
    }

    @Provides
    fun stuffInteractor(): DataInteractor {
        return DataInteractor()
    }

    @Provides
    fun thingInteractor(): ColorInteractor {
        return ColorInteractor()
    }
}