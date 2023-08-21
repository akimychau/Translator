package com.example.translator.presentation.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.translator.data.data.AppState
import com.example.translator.data.data.DataModel
import com.example.translator.databinding.ActivityMainBinding
import com.example.translator.presentation.presenter.MainPresenterImpl
import com.example.translator.presentation.presenter.Presenter
import com.example.translator.presentation.view.base.BaseActivity
import com.example.translator.presentation.view.base.View
import com.example.translator.presentation.view.main.adapter.MainAdapter

class MainActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenterImpl()
    }

    override fun renderData(appState: List<DataModel>) {

        if (adapter == null) {
            binding.rvDescriptionList.layoutManager =
                LinearLayoutManager(applicationContext)
            binding.rvDescriptionList.adapter =
                MainAdapter(onListItemClickListener, appState)
        } else {
            adapter!!.setData(appState)
        }
    }
}