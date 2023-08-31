package com.example.translator.di.modules

import android.app.Application
import com.example.translator.App
import com.example.translator.data.connectivitylistener.ConnectivityListener
import com.example.translator.data.data.DataModel
import com.example.translator.data.datasource.api.ApiService
import com.example.translator.data.datasource.api.RetrofitImplementation
import com.example.translator.data.repository.Repository
import com.example.translator.data.repository.RepositoryImplementation
import com.example.translator.utils.ConnectivityListenerImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideRepository(
        dataSourceRemote: ApiService,
        networkStatus: ConnectivityListener
    ): Repository<List<DataModel>> = RepositoryImplementation(dataSourceRemote, networkStatus)

    @Provides
    @Singleton
    internal fun provideApiService(): ApiService = RetrofitImplementation()

    @Provides
    @Singleton
    fun networkStatus(app: Application): ConnectivityListener =
        ConnectivityListenerImplementation(app)
}