package com.rfonzi.mvi_sample.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.rfonzi.mvi_sample.compose.navigation.MainScreen
import com.rfonzi.mvi_sample.compose.ui.theme.MvisampleTheme
import com.rfonzi.mvi_sample.shared.MainPresenter
import com.rfonzi.mvi_sample.shared.uiIntents.LoadFirstPageIntent
import com.rfonzi.mvi_sample.shared.uiModels.InitialLoading
import com.rfonzi.mvi_sample.shared.uiModels.Pages
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
