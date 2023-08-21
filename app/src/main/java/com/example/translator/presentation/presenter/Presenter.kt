package com.example.translator.presentation.presenter

import com.example.translator.data.data.AppState
import com.example.translator.presentation.view.base.View

interface Presenter<T : AppState, V : View> {

    fun getData(word: String, isOnline: Boolean)

    fun attachView(view: V)

    fun detachView(view: V)
}