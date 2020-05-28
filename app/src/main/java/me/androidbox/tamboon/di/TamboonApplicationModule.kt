package me.androidbox.tamboon.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TamboonApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application
}
