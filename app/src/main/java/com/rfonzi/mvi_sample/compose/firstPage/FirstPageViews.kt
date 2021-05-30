package com.rfonzi.mvi_sample.compose.firstPage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rfonzi.mvi_sample.shared.uiModels.FirstPage
import com.rfonzi.mvi_sample.shared.uiModels.HorizontalDataModel
import com.rfonzi.mvi_sample.shared.uiModels.VerticalDataModel

@Composable
fun DataPage(
    modifier: Modifier = Modifier,
    model: FirstPage
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}