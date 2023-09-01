package com.example.translator.presentation.view.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translator.R
import com.example.translator.data.data.AppState
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.presentation.view.base.BaseActivity
import com.example.translator.presentation.view.main.adapter.MainAdapter
import com.example.translator.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<AppState>() {

    override lateinit var model: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val adapter: MainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    private fun initViewModel() {

        if (binding.mainActivityRecyclerView.adapter != null) {
            throw IllegalStateException(getString(R.string.viewmodel_first_initialisation_exception))
        }

        val viewModel: MainViewModel by viewModel()
        model = viewModel
        viewModel.subscribe().observe(this@MainActivity) { renderData(it) }
    }

    private fun initViews() {

        binding.inputLayout.setEndIconOnClickListener {
            model.getData(binding.inputEditText.text.toString())
        }

        binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerView.adapter = adapter
    }

    override fun renderData(appState: AppState) {

        when (appState) {
            is AppState.Success -> {
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.setData(data)
                }
            }

            is AppState.Loading -> {
                showViewLoading()
                binding.progressBarRound.visibility = android.view.View.VISIBLE
            }

            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData(getString(R.string.hi))
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = android.view.View.VISIBLE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.VISIBLE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.VISIBLE
    }
}