package com.example.translator.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.translator.data.data.AppState
import com.example.translator.domain.MainInteractor
import io.reactivex.rxjava3.disposables.Disposable

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {

        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(doOnSubscribe())
                .subscribe({ state ->
                    appState = state
                    liveDataForViewToObserve.value = state
                }, { error ->
                    liveDataForViewToObserve.value = AppState.Error(error)
                })
        )
    }

    private fun doOnSubscribe(): (t: Disposable) -> Unit =
        { liveDataForViewToObserve.value = AppState.Loading }
}