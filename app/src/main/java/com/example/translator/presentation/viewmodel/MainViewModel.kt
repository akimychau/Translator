package com.example.translator.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.translator.data.data.AppState
import com.example.translator.data.datasource.api.RetrofitImplementation
import com.example.translator.data.repository.RepositoryImplementation
import com.example.translator.domain.MainInteractor

class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(
            RetrofitImplementation(),
            true
        )
    )
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String): LiveData<AppState> {

        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { liveDataForViewToObserve.value = AppState.Loading }
                .subscribe(
                    { state ->
                        appState = state
                        liveDataForViewToObserve.value = state
                    }, { error ->
                        liveDataForViewToObserve.value = AppState.Error(error)
                    }
                )
        )
        return super.getData(word)
    }
}