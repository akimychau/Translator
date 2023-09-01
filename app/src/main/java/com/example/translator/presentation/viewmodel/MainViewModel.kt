package com.example.translator.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.translator.data.data.AppState
import com.example.translator.domain.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {

        liveDataForViewToObserve.value = AppState.Loading
        cancelJob()

        viewModelCoroutineScope.launch { startInteractor(word) }
    }

    private suspend fun startInteractor(word: String) =
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(interactor.getData(word))
        }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }

}