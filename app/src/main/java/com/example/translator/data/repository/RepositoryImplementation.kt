package com.example.translator.data.repository

import com.example.translator.data.connectivitylistener.ConnectivityListener
import com.example.translator.data.data.DataModel
import com.example.translator.data.datasource.DataSource

class RepositoryImplementation(
    private val dataSource: DataSource<List<DataModel>>,
    private val networkStatus: ConnectivityListener
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return if (networkStatus.isOnline()) {
            dataSource.getData(word)
        } else {
            dataSource.getData(word)
        }
    }
}