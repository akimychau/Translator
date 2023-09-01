package com.example.translator.domain

import com.example.translator.data.data.AppState
import com.example.translator.data.data.DataModel
import com.example.translator.data.repository.Repository
import io.reactivex.rxjava3.core.Observable

class MainInteractor(private val repository: Repository<List<DataModel>>) :
    Interactor<AppState> {

    override fun getData(word: String): Observable<AppState> {
        return repository.getData(word).map { AppState.Success(it) }
    }

}