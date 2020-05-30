package me.androidbox.tamboon.di

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.presentation.routers.*
import me.androidbox.tamboon.presentation.screens.CharitySelectedListener
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import me.androidbox.tamboon.scopes.ActivityScope
import me.androidbox.tamboon.utils.ViewModelProviderFactory

@Module
class TamboonActivityModule(private val tamboonActivity: TamboonActivity) {

    @ActivityScope
    @Provides
    fun provideTamboonViewModel(
        requestCharities: RequestCharities,
        requestDonation: RequestDonation
    ): TamboonViewModel {
        return ViewModelProviders.of(
            tamboonActivity,
            ViewModelProviderFactory(TamboonViewModel::class) {
                TamboonViewModel(
                    requestCharities,
                    requestDonation
                )
            }).get(TamboonViewModel::class.java)
    }

    @ActivityScope
    @Provides
    fun provideLoadingFragmentRouter(): LoadingFragmentRouter =
        LoadingFragmentRouterImp(tamboonActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideCharitiesFragmentRouter(): CharitiesFragmentRouter =
        CharitiesFragmentRouterImp(tamboonActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideDonationsFragmentRouter(): DonationFragmentRouter =
        DonationFragmentRouterImp(tamboonActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideCharitiesSelectedListener(): CharitySelectedListener = tamboonActivity
}
