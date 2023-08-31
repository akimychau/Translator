package com.example.translator.di

import android.app.Application
import com.example.translator.App
import com.example.translator.di.modules.ActivityModule
import com.example.translator.di.modules.InteractorModule
import com.example.translator.di.modules.RepositoryModule
import com.example.translator.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ActivityModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}