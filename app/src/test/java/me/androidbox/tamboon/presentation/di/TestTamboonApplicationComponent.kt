package me.androidbox.tamboon.presentation.di

import dagger.Component
import me.androidbox.tamboon.presentation.screens.TamboonActivityTest
import javax.inject.Singleton

@Singleton
@Component(modules = [TestTamboonApplicationModule::class])
interface TestTamboonApplicationComponent {
    fun inject(activity: TamboonActivityTest)
}
