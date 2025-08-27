package com.ozalp.velorasports.data.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}