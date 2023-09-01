package com.example.translator.di

import com.example.translator.data.data.DataModel
import com.example.translator.data.datasource.api.RetrofitImplementation
import com.example.translator.data.repository.Repository
import com.example.translator.data.repository.RepositoryImplementation
import com.example.translator.domain.MainInteractor
import com.example.translator.presentation.viewmodel.MainViewModel
import com.example.translator.utils.ConnectivityListenerImplementation
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>> {
        RepositoryImplementation(
            RetrofitImplementation(),
            ConnectivityListenerImplementation()
        )
    }
}

val mainScreen = module {
    factory { MainInteractor(get()) }
    factory { MainViewModel(get()) }
}