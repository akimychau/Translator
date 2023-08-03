package com.example.translator.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.translator.model.data.AppState
import com.example.translator.presenter.Presenter

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenterByMoxy(): Presenter<T, View>

    abstract override fun renderData(appState: AppState)
}