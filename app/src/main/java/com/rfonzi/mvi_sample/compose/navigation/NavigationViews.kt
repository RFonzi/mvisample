package com.rfonzi.mvi_sample.compose.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import com.rfonzi.mvi_sample.compose.firstPage.DataPage
import com.rfonzi.mvi_sample.compose.secondPage.ColorPage
import com.rfonzi.mvi_sample.shared.domainModels.ColorModel
import com.rfonzi.mvi_sample.shared.uiIntents.LoadFirstPageIntent
import com.rfonzi.mvi_sample.shared.uiIntents.LoadSecondPageIntent
import com.rfonzi.mvi_sample.shared.uiIntents.MainIntent
import com.rfonzi.mvi_sample.shared.uiIntents.SelectThingIntent
import com.rfonzi.mvi_sample.shared.uiModels.FirstPage
import com.rfonzi.mvi_sample.shared.uiModels.Pages
import com.rfonzi.mvi_sample.shared.uiModels.SecondPage

@Composable
fun MainScreen(model: Pages, onIntent: (MainIntent) -> Unit) {
    val firstPageSelected = when (model) {
        is FirstPage -> true
        is SecondPage -> false
    }

    Scaffold(
        bottomBar = { ScreenNavigation(firstPageSelected) { onIntent(it) } },
    ) {
        ScreenContents(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                start = it.calculateStartPadding(LocalLayoutDirection.current),
                bottom = it.calculateBottomPadding(),
                end = it.calculateEndPadding(LocalLayoutDirection.current)
            ),
            model = model
        ) { newColorModel ->
            onIntent(SelectThingIntent(newColorModel))
        }
    }

}

@Composable
fun ScreenContents(
    modifier: Modifier = Modifier,
    model: Pages,
    clickedModel: (ColorModel) -> Unit
) {
    when (model) {
        is FirstPage -> DataPage(
            modifier = modifier,
            model = model
        )
        is SecondPage -> ColorPage(
            modifier = modifier,
            model = model
        ) {
            clickedModel(it)
        }
    }
}

@Preview
@Composable
fun ScreenNavigation(firstPageSelected: Boolean = true, onIntent: (MainIntent) -> Unit = {}) {
    BottomNavigation {
        BottomNavigationItem(
            selected = firstPageSelected,
            onClick = { onIntent(LoadFirstPageIntent()) },
            icon = {
                Image(imageVector = Icons.Default.Star, contentDescription = "First Page")
            },
            label = {
                Text(text = "First")
            }
        )
        BottomNavigationItem(
            selected = !firstPageSelected,
            onClick = { onIntent(LoadSecondPageIntent()) },
            icon = {
                Image(imageVector = Icons.Default.Refresh, contentDescription = "Second Page")
            },
            label = {
                Text(text = "Second")
            }
        )
    }
}