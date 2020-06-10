package me.androidbox.tamboon.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
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
}
