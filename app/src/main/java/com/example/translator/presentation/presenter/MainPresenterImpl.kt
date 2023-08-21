package com.example.translator.presentation.presenter

import com.example.translator.data.data.AppState
import com.example.translator.data.repository.RepositoryImpl
import com.example.translator.presentation.view.base.View

class MainPresenterImpl<T : AppState, V : View>(
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : Presenter<T, V> {

    private var currentView: V? = null

    override fun getData(word: String, isOnline: Boolean) {

        currentView?.renderData(repositoryImpl.getData(word))
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
        getData("asdasd", true)
    }
}