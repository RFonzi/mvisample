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
                "red" to 0xFFF44336,
                "pink" to 0xFFE91E63,
                "purple" to 0xFF9C27B0,
                "deep purple" to 0xFF673AB7,
                "indigo" to 0xFF3F51B5,
                "blue" to 0xFF2196F3,
                "light blue" to 0xFF03A9F4,
                "cyan" to 0xFF00BCD4,
                "teal" to 0xFF009688,
                "green" to 0xFF4CAF50,
                "light green" to 0xFF8BC34A,
                "lime" to 0xFFCDDC39,
                "yellow" to 0xFFFFEB3B,
                "amber" to 0xFFFFC107,
                "orange" to 0xFFFF9800,
                "deep orange" to 0xFFFF5722
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