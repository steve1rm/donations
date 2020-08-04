package me.androidbox.tamboon.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import me.androidbox.tamboon.utils.SchedulerProvider
import me.androidbox.tamboon.utils.SchedulerProviderImp

@Module
class AndroidTestApplicationModule(private val application: TamboonApplication) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Application = application

    @Singleton
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideSchedulers(): SchedulerProvider = SchedulerProviderImp()
}
