package com.rfonzi.mvi_sample.shared

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class StuffInteractor @Inject constructor() {

    suspend fun getStuff(): List<Stuff> {
        delay(1000) // simulate network response time
        val random = Random(System.currentTimeMillis())
        return (1..10.coerceAtMost(random.nextInt(40)))
            .map {
                Stuff(
                    it,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                )
            }
    }
}