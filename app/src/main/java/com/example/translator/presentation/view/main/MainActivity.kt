package com.example.translator.presentation.view.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translator.R
import com.example.translator.data.data.AppState
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.presentation.view.base.BaseActivity
import com.example.translator.presentation.view.main.adapter.MainAdapter
import com.example.translator.presentation.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity<AppState>() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModelFactory.create(MainViewModel::class.java)
        viewModel.subscribe().observe(this@MainActivity) {
            renderData(it)
        }

        binding.inputLayout.setEndIconOnClickListener {
            viewModel.getData(binding.inputEditText.text.toString())
        }
    }

    override fun renderData(appState: AppState) {

        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.rvDescriptionList.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.rvDescriptionList.adapter =
                            MainAdapter(dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }

            is AppState.Loading -> {
                showViewLoading()
                binding.progressBarHorizontal.visibility = android.view.View.GONE
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
            viewModel.getData(getString(R.string.hi))
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