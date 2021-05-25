package com.rfonzi.mvi_sample.regular

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rfonzi.mvi_sample.databinding.ActivityMainBinding
import com.rfonzi.mvi_sample.shared.MainScreen
import com.rfonzi.mvi_sample.shared.Stuff
import com.rfonzi.mvi_sample.shared.UiPresenter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var uiPresenter: UiPresenter

    val stuffListView: RecyclerView by lazy { binding.listView }
    val stuffAdapter = StuffAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model = uiPresenter.model


        val stuffListView = binding.listView
        val stuffAdapter = StuffAdapter()
        stuffListView.adapter = stuffAdapter
        stuffListView.layoutManager = LinearLayoutManager(this)

    }

    fun CoroutineScope.listenToModel(state: StateFlow<MainScreen>) {
        state.onEach {
            render(it)
        }
    }

    fun render(screen: MainScreen) {
        when (screen) {
            is MainScreen.FirstPage -> renderFirstPage(screen)
            is MainScreen.SecondPage -> TODO()
        }
    }

    fun renderFirstPage(screen: MainScreen.FirstPage) {
        val stuffListView = binding.listView
        val stuffAdapter
    }
}