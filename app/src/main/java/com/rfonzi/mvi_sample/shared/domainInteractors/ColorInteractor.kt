package com.rfonzi.mvi_sample.shared.domainInteractors

import com.rfonzi.mvi_sample.shared.domainModels.ColorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ColorInteractor @Inject constructor() {

    suspend fun getThings(): List<ColorModel> {
        return withContext(Dispatchers.IO) {

            // simulate network response time
            delay(1000)

            listOf(
                "red" to "#F44336",
                "pink" to "#E91E63",
                "purple" to "#9C27B0",
                "deep purple" to "#673AB7",
                "indigo" to "#3F51B5",
                "blue" to "#2196F3",
                "light blue" to "#03A9F4",
                "cyan" to "#00BCD4",
                "teal" to "#009688",
                "green" to "#4CAF50",
                "light green" to "#8BC34A",
                "lime" to "#CDDC39",
                "yellow" to "#FFEB3B",
                "amber" to "#FFC107",
                "orange" to "#FF9800",
                "deep orange" to "#FF5722"
            ).mapIndexed { index, pair ->
                ColorModel(
                    id = index,
                    description = pair.first,
                    color = pair.second
                )
            }
        }
    }
}