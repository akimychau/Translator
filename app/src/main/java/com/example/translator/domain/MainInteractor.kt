package com.example.translator.domain

import com.example.translator.data.data.AppState
import com.example.translator.data.data.DataModel
import com.example.translator.data.repository.Repository

class MainInteractor(private val repository: Repository<List<DataModel>>) :
    Interactor<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Success(
            repository.getData(word)
        )
    }
}