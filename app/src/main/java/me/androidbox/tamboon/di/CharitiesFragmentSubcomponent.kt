package me.androidbox.tamboon.di

import dagger.Subcomponent
import me.androidbox.tamboon.presentation.screens.CharitiesFragment
import me.androidbox.tamboon.scopes.FragmentScope

@FragmentScope
@Subcomponent(modules = [CharitiesFragmentModule::class])
interface CharitiesFragmentSubcomponent {
    fun inject(charitiesFragment: CharitiesFragment)
}
