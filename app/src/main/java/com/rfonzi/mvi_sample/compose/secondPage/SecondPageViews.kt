package com.rfonzi.mvi_sample.compose.secondPage

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rfonzi.mvi_sample.shared.domainModels.ColorModel
import com.rfonzi.mvi_sample.shared.uiModels.SecondPage

@Composable
fun ColorPage(
    modifier: Modifier = Modifier,
    model: SecondPage,
    clickedModel: (ColorModel) -> Unit
) {
    when (model) {
        is SecondPage.ColorsVisible -> ColorsVisiblePage(model = model) {
            clickedModel(it)
        }
        is SecondPage.Loading -> ColorsLoading()
    }

}

@Composable
fun ColorsLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun ColorsVisiblePage(
    model: SecondPage.ColorsVisible,
    clickedModel: (ColorModel) -> Unit
) {
    val defaultColor = MaterialTheme.colors.surface
    val color: Color by remember {
        mutableStateOf(Color(model.chosenColorModel?.color ?: defaultColor.value.toLong()))
    }
    val selectedColor: Color by animateColorAsState(
        targetValue = color,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutLinearInEasing
        )
    )

    Column {
        SelectedColorPreview(color = selectedColor)
        ColorList(model.listOfColorModels) {
            clickedModel(it)
        }
    }
}

@Preview
@Composable
fun SelectedColorPreview(
    color: Color = MaterialTheme.colors.primary
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(color)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColorList(
    listOfColorModels: List<ColorModel>,
    clickedModel: (ColorModel) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(80.dp),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 16.dp
        ),

    ) {
        items(listOfColorModels) { item ->
            Box(modifier = Modifier.padding(
                start = 8.dp,
                top = 8.dp

            )) {
                ColorItem(item) {
                    clickedModel(it)
                }
            }
        }
    }
}

@Preview
@Composable
fun ColorItem(
    model: ColorModel = ColorModel(1, "Green", 0xFF00FF00),
    onClick: (ColorModel) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .width(60.dp)
            .clickable {
                onClick(model)
            }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(model.color))
            )
            Text(
                text = model.description,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}