package com.example.translator.data.repository

import com.example.translator.data.connectivitylistener.ConnectivityListener
import com.example.translator.data.data.DataModel
import com.example.translator.data.datasource.api.ApiService
import io.reactivex.rxjava3.core.Observable

    class RepositoryImplementation(
        private val dataSource: ApiService,
        private val networkStatus: ConnectivityListener
    ) :
        Repository<List<DataModel>> {

        override fun getData(word: String): Observable<List<DataModel>> {
            return if (networkStatus.isOnline()) {
                dataSource.getData(word)
            } else {
                dataSource.getData(word)
            }
        }
    }