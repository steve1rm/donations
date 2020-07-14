package me.androidbox.tamboon.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import me.androidbox.tamboon.presentation.screens.DonationFragment
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import me.androidbox.tamboon.scopes.FragmentScope
import me.androidbox.tamboon.utils.*

@Module
class FragmentModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideCharitiesAdapter(): CharitiesAdapter = CharitiesAdapter()

    @FragmentScope
    @Provides
    fun provideCreditCardTokenFactoryImp(clientFactory: ClientFactory,
                                         cardParamFactory: CardParamFactory): CreditCardTokenFactory {
        val donationFragment = fragment as DonationFragment

        return CreditCardTokenFactoryImp(
            clientFactory,
            cardParamFactory,
            donationFragment::onRequestFailed,
            donationFragment::onRequestSuccess)
    }

    @FragmentScope
    @Provides
    fun provideTamboonViewModel(
        requestCharities: RequestCharities,
        requestDonation: RequestDonation,
        schedulerProvider: SchedulerProvider
    ): TamboonViewModel {
        return ViewModelProvider(
            fragment,
            ViewModelProviderFactory(TamboonViewModel::class) {
                TamboonViewModel(
                    requestCharities,
                    requestDonation,
                    backgroundScheduler = schedulerProvider.background(),
                    uiScheduler = schedulerProvider.ui()
                )
            }).get(TamboonViewModel::class.java)
    }
}
