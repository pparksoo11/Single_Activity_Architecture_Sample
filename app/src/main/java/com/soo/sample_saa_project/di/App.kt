package com.soo.sample_saa_project.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        private lateinit var application: App
        fun getInstance() = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}