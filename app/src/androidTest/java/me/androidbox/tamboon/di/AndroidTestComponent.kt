package me.androidbox.tamboon.di

import dagger.Component
import me.androidbox.tamboon.automation.TamboonActivityAndroidTest
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidTestApplicationModule::class,
    AndroidTestNetworkModule::class])
interface AndroidTestComponent : TamboonApplicationComponent {
    fun inject(activity: TamboonActivityAndroidTest)
}
