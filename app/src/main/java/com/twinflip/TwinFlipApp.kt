package com.twinflip

import android.app.Application
import com.twinflip.di.appModule
import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TwinFlipApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TwinFlipApp)
            analytics()
            modules(appModule)
        }
    }
}