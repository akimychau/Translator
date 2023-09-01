package com.example.translator.presentation.view.base

import androidx.appcompat.app.AppCompatActivity
import com.example.translator.data.data.AppState
import com.example.translator.presentation.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}