package me.androidbox.tamboon.di

import dagger.Component
import me.androidbox.tamboon.presentation.TamboonActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TamboonApplicationModule::class,
    NetworkModule::class])
interface TamboonApplicationComponent {
    fun inject(tamboonActivity: TamboonActivity)
}
