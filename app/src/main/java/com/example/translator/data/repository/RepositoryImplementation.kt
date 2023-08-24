package com.example.translator.data.repository

import com.example.translator.data.data.DataModel
import com.example.translator.data.datasource.api.ApiService
import io.reactivex.rxjava3.core.Observable

class RepositoryImplementation(
    private val dataSource: ApiService,
    private val isOnline: Boolean
) :
    Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return if (isOnline) {
            dataSource.getData(word)
        } else {
            dataSource.getData(word)
        }
    }
}