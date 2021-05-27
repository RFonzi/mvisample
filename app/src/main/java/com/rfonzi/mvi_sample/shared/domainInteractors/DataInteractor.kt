package com.rfonzi.mvi_sample.shared.domainInteractors

import com.rfonzi.mvi_sample.shared.domainModels.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class DataInteractor @Inject constructor() {

    suspend fun getData(): List<DataModel> {
        return withContext(Dispatchers.IO) {

            // simulate network response time
            delay(1000)

            val random = Random(System.currentTimeMillis())
            (1..10.coerceAtMost(random.nextInt(40)))
                .map {
                    DataModel(
                        it,
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                    )
                }
        }

    }
}