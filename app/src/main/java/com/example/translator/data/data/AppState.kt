package com.example.translator.data.data

sealed class AppState {

    data class Success(val data: List<DataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
