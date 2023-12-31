package com.example.translator

import android.app.Application
import com.example.translator.di.application
import com.example.translator.di.mainScreen
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin { modules(listOf(application, mainScreen)) }
    }
}