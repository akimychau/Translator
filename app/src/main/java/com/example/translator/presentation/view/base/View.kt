package com.example.translator.presentation.view.base

import com.example.translator.data.data.AppState
import com.example.translator.data.data.DataModel

interface View {

    fun renderData(appState: AppState)
}