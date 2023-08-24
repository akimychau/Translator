package com.example.translator.presentation.presenter

import com.example.translator.data.data.AppState
import com.example.translator.data.datasource.api.RetrofitImplementation
import com.example.translator.data.repository.RepositoryImplementation
import com.example.translator.domain.MainInteractor
import com.example.translator.presentation.view.base.View
import com.example.translator.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainPresenterImpl<T : AppState, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(
            RetrofitImplementation(),
            true
        )
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun getData(word: String) {

        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading) }
                .subscribe(
                    {
                        currentView?.renderData(it)
                    }, {
                        currentView?.renderData(AppState.Error(it))
                    }
                )
        )
    }

    override fun detachView(view: V) {
        if (view == currentView) {
            currentView = null
        }
    }

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }
}