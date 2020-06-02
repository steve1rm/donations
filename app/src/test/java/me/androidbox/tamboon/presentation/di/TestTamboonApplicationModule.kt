package me.androidbox.tamboon.presentation.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.presentation.routers.HomeMenuFragmentRouter
import javax.inject.Singleton

@Module
class TestTamboonApplicationModule {

    @Singleton
    @Provides
    fun provideHomeMenuFragmentRouter(): HomeMenuFragmentRouter = mock()
}