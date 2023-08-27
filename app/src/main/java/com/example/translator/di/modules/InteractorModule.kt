package com.example.translator.di.modules

import com.example.translator.data.data.DataModel
import com.example.translator.data.repository.Repository
import com.example.translator.domain.MainInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(repository: Repository<List<DataModel>>) =
        MainInteractor(repository)
}