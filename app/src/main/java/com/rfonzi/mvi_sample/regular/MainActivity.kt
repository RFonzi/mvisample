package com.rfonzi.mvi_sample.regular

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.R
import com.rfonzi.mvi_sample.databinding.ActivityMainBinding
import com.rfonzi.mvi_sample.shared.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import reactivecircus.flowbinding.material.itemSelections
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

            merge(
                flowOf(LoadFirstPageIntent()),
                binding.secondPage.intents,
                observeBottomNavigation()

            ).distinctUntilChanged().onEach {
                Log.d("Asdf", "Sent intent $it")
            }.onCompletion {
                Log.d("Asdf", "Intent flow completed")
            }.collect {
                uiPresenter.sendIntent(it)
            }

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
        binding.firstPage.visibility = View.VISIBLE
        binding.secondPage.visibility = View.GONE
        binding.firstPage.render(screen)
    }


    fun renderSecondPage(screen: SecondPage) {
        binding.firstPage.visibility = View.GONE
        binding.secondPage.visibility = View.VISIBLE
        binding.secondPage.render(screen)
    }

    private fun observeBottomNavigation(): Flow<MainIntent> {
        return binding.bottomNavigation.itemSelections()
            .map {
                when (it.itemId) {
                    R.id.menu_first_page -> LoadFirstPageIntent()
                    R.id.menu_second_page -> LoadSecondPageIntent()
                    else -> null
                }
            }
            .filterNotNull()
    }
}