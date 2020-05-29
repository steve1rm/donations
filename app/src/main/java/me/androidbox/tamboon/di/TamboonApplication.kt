package me.androidbox.tamboon.di

import android.app.Application
import timber.log.Timber

class TamboonApplication : Application() {
    lateinit var tamboonApplicationComponent: TamboonApplicationComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        tamboonApplicationComponent = DaggerTamboonApplicationComponent
            .builder()
            .tamboonApplicationModule(TamboonApplicationModule(this@TamboonApplication))
            .build()
    }
}
