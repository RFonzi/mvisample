package com.rfonzi.mvi_sample.regular

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.databinding.ActivityMainBinding
import com.rfonzi.mvi_sample.shared.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var uiPresenter: UiPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            uiPresenter.model
                .onEach { render(it) }
                .launchIn(this@launch)

            uiPresenter.sendIntent(LoadFirstPageIntent())
        }
    }

    fun render(screen: MainScreen) {
        when (screen) {
            is FirstPage -> renderFirstPage(screen)
            is InitialLoading -> renderInitialLoading()
            is SecondPage -> renderSecondPage(screen)
        }
    }

    fun renderInitialLoading() {

    }

    fun renderFirstPage(screen: FirstPage) {
        when(screen) {
            is FirstPage.ContentVisible -> binding.firstPage.render(screen)
            is FirstPage.Loading -> renderFirstPageLoading()
        }
    }


    fun renderFirstPageLoading() {

    }

    fun renderSecondPage(screen: SecondPage) {

    }
}