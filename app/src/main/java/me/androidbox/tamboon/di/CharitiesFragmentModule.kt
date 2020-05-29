package me.androidbox.tamboon.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import me.androidbox.tamboon.scopes.FragmentScope

@Module
class CharitiesFragmentModule(private val charitiesFragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideCharitiesAdapter(): CharitiesAdapter = CharitiesAdapter()
}
