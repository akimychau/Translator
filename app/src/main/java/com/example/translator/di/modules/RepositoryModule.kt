package com.example.translator.di.modules

import com.example.translator.data.data.DataModel
import com.example.translator.data.datasource.api.ApiService
import com.example.translator.data.datasource.api.RetrofitImplementation
import com.example.translator.data.repository.Repository
import com.example.translator.data.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideRepository(
        dataSourceRemote: ApiService,
        isOnline: Boolean
    ): Repository<List<DataModel>> = RepositoryImplementation(dataSourceRemote, isOnline)

    @Provides
    @Singleton
    internal fun provideApiService(): ApiService = RetrofitImplementation()

    @Provides
    @Singleton
    internal fun networkStatus(): Boolean = true
}