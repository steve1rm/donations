package me.androidbox.tamboon.di

import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.databinding.FragmentHomemenuBinding
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.presentation.routers.*
import me.androidbox.tamboon.presentation.screens.listeners.CharitySelectedListener
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import me.androidbox.tamboon.scopes.ActivityScope
import me.androidbox.tamboon.utils.SchedulerProvider
import me.androidbox.tamboon.utils.ViewModelProviderFactory

@Module
class TamboonActivityModule(private val tamboonActivity: TamboonActivity) {

    @ActivityScope
    @Provides
    fun provideTamboonViewModel(
        requestCharities: RequestCharities,
        requestDonation: RequestDonation,
        schedulerProvider: SchedulerProvider
    ): TamboonViewModel {
        return ViewModelProviders.of(
            tamboonActivity,
            ViewModelProviderFactory(TamboonViewModel::class) {
                TamboonViewModel(
                    requestCharities,
                    requestDonation,
                    backgroundScheduler = schedulerProvider.background(),
                    uiScheduler = schedulerProvider.ui()
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
    fun provideSuccessFragmentRouter(): SuccessFragmentRouter =
        SuccessFragmentRouterImp(tamboonActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideDonationsFragmentRouter(): DonationFragmentRouter =
        DonationFragmentRouterImp(tamboonActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideHomeMenuFragmentRouter(): HomeMenuFragmentRouter =
        HomeMenuFragmentRouterImp(tamboonActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideCharitiesSelectedListener(): CharitySelectedListener = tamboonActivity
}
