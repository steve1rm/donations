package me.androidbox.tamboon.di

import dagger.Component
import javax.inject.Singleton
import me.androidbox.tamboon.automation.TamboonActivityAndroidTest

@Singleton
@Component(modules = [
    AndroidTestApplicationModule::class,
    AndroidTestNetworkModule::class])
interface AndroidTestComponent : TamboonApplicationComponent {
    fun inject(activity: TamboonActivityAndroidTest)
}
