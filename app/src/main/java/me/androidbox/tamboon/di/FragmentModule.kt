package me.androidbox.tamboon.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import me.androidbox.tamboon.presentation.screens.listeners.CharitySelectedListener
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import me.androidbox.tamboon.presentation.screens.listeners.SubmitDonationListener
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import me.androidbox.tamboon.scopes.FragmentScope
import me.androidbox.tamboon.utils.ViewModelProviderFactory

@Module
class FragmentModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideCharitiesAdapter(): CharitiesAdapter = CharitiesAdapter()

    @FragmentScope
    @Provides
    fun provideCharitiesSelectedListener(): CharitySelectedListener =
        fragment.activity as TamboonActivity

    @FragmentScope
    @Provides
    fun provideSubmitDonationListener(): SubmitDonationListener =
        fragment.activity as TamboonActivity

    @FragmentScope
    @Provides
    fun provideTamboonViewModel(
        requestCharities: RequestCharities,
        requestDonation: RequestDonation
    ): TamboonViewModel {
        return ViewModelProviders.of(
            fragment,
            ViewModelProviderFactory(TamboonViewModel::class) {
                TamboonViewModel(
                    requestCharities,
                    requestDonation
                )
            }).get(TamboonViewModel::class.java)
    }

}
