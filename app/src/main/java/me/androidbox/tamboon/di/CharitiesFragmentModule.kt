package me.androidbox.tamboon.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.presentation.screens.TamboonViewModel
import me.androidbox.tamboon.scopes.FragmentScope
import me.androidbox.tamboon.utils.ViewModelProviderFactory

@Module
class CharitiesFragmentModule(private val charitiesFragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideTamboonViewModel(requestCharities: RequestCharities,
                                requestDonation: RequestDonation
    ): TamboonViewModel {
        return ViewModelProviders.of(
            charitiesFragment,
            ViewModelProviderFactory(TamboonViewModel::class) {
                TamboonViewModel(
                    requestCharities,
                    requestDonation
                )
            }).get(TamboonViewModel::class.java)
    }

}