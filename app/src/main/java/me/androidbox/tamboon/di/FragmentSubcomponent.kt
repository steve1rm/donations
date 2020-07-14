package me.androidbox.tamboon.di

import dagger.Subcomponent
import me.androidbox.tamboon.presentation.screens.CharitiesFragment
import me.androidbox.tamboon.presentation.screens.DonationFragment
import me.androidbox.tamboon.presentation.screens.HomeMenuFragment
import me.androidbox.tamboon.presentation.screens.LoadingFragment
import me.androidbox.tamboon.scopes.FragmentScope

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentSubcomponent {
    fun inject(charitiesFragment: CharitiesFragment)
    fun inject(donationFragment: DonationFragment)
    fun inject(homeMenuFragment: HomeMenuFragment)
    fun inject(loadingFragment: LoadingFragment)
}
