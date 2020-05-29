package me.androidbox.tamboon.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TamboonApplicationModule::class,
    NetworkModule::class])
interface TamboonApplicationComponent {
    fun tamboonActivitySubComponent(tamboonActivityModule: TamboonActivityModule): TamboonActivitySubComponent
}

