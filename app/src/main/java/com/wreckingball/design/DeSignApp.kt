package com.wreckingball.design

import android.app.Application
import com.wreckingball.design.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DeSignApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DeSignApp)
            modules(appModule)
        }
    }
}