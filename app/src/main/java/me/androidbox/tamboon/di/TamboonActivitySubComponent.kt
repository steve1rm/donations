package me.androidbox.tamboon.di

import dagger.Subcomponent
import me.androidbox.tamboon.presentation.TamboonActivity
import me.androidbox.tamboon.scopes.ActivityScope

@ActivityScope
@Subcomponent(modules = [TamboonActivityModule::class])
interface TamboonActivitySubComponent {
    fun inject(tamboonActivity: TamboonActivity)
}
