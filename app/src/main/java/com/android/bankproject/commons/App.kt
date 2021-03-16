package com.android.bankproject.commons

import android.app.Application
import com.android.data.di.dataModule
import com.android.bankproject.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, uiModule))
        }
    }
}