package com.twinflip.app

import android.app.Application
import com.twinflip.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TwinFlipApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TwinFlipApp)
            modules(appModule)
        }
    }
}