package com.rfonzi.mvi_sample.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.rfonzi.mvi_sample.compose.ui.theme.MvisampleTheme
import com.rfonzi.mvi_sample.shared.MainPresenter
import com.rfonzi.mvi_sample.shared.uiIntents.LoadFirstPageIntent
import com.rfonzi.mvi_sample.shared.uiIntents.LoadSecondPageIntent
import com.rfonzi.mvi_sample.shared.uiIntents.MainIntent
import com.rfonzi.mvi_sample.shared.uiModels.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MvisampleTheme {

                val model = mainPresenter.model.collectAsState()

                when (val state = model.value) {
                    is InitialLoading -> {
                        lifecycleScope.launchWhenResumed {
                            mainPresenter.sendIntent(LoadFirstPageIntent())
                        }
                    }
                    is Pages -> MainScreen(model = state) {
                        lifecycleScope.launch { mainPresenter.sendIntent(it) }
                    }
                }
            }
        }
    }
}

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
            model = model,
            onIntent = onIntent
        )
    }

}

@Composable
fun ScreenContents(
    modifier: Modifier = Modifier,
    model: Pages,
    onIntent: (MainIntent) -> Unit
) {
    when (model) {
        is FirstPage -> DataPage(
            modifier = modifier,
            model = model
        ) {
            onIntent(it)
        }
        is SecondPage -> TODO()
    }
}

@Composable
fun DataPage(
    modifier: Modifier = Modifier,
    model: FirstPage,
    onIntent: (MainIntent) -> Unit
) {
    when (model) {
        is FirstPage.DataVisible -> DataPageContents(
            modifier = modifier,
            model = model
        )
        is FirstPage.Loading -> DataPageLoading()
    }
}

@Composable
fun DataPageContents(
    modifier: Modifier = Modifier,
    model: FirstPage.DataVisible
) {
    Column(
        modifier = modifier
    ) {
        HorizontalDataList(list = model.listOfHorizontalDataModels)
        VerticalDataList(list = model.listOfVerticalDataModels)
    }
}

@Composable
fun HorizontalDataList(list: List<HorizontalDataModel>) {
    LazyRow(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp,
            bottom = 4.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { item ->
            HorizontalDataCard(item)
        }
    }
}

@Preview
@Composable
fun HorizontalDataCard(model: HorizontalDataModel = HorizontalDataModel(1)) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = model.id.toString())
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = model.data.toString(),
                maxLines = 4
            )
        }
    }
}

@Composable
fun VerticalDataList(list: List<VerticalDataModel>) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = 8.dp,
            bottom = 4.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { item ->
            VerticalDataCard(item)
        }
    }
}

@Preview
@Composable
fun VerticalDataCard(item: VerticalDataModel = VerticalDataModel(1, "Lorem ipsum")) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = item.id.toString(),
                modifier = Modifier.align(Alignment.End)
            )
            Text(
                text = item.description,
                maxLines = 3,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = item.data.toString(),
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun DataPageLoading() {
    CircularProgressIndicator(
        modifier = Modifier.fillMaxSize()
    )
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