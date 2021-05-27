package com.rfonzi.mvi_sample.shared

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun uiPresenter(stuffInteractor: StuffInteractor): UiPresenter {
        return UiPresenter(stuffInteractor)
    }

    @Provides
    fun stuffInteractor(): StuffInteractor {
        return StuffInteractor()
    }
}