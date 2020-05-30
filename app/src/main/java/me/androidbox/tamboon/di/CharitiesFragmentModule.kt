package me.androidbox.tamboon.di

import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import me.androidbox.tamboon.presentation.screens.CharitiesFragment
import me.androidbox.tamboon.presentation.screens.CharitySelectedListener
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import me.androidbox.tamboon.scopes.FragmentScope

@Module
class CharitiesFragmentModule(private val charitiesFragment: CharitiesFragment) {

    @FragmentScope
    @Provides
    fun provideCharitiesAdapter(): CharitiesAdapter = CharitiesAdapter()

    @FragmentScope
    @Provides
    fun provideCharitiesSelectedListener(): CharitySelectedListener =
        charitiesFragment.activity as TamboonActivity
}
