package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.di.TamboonActivityModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.routers.*
import me.androidbox.tamboon.presentation.screens.listeners.CharitySelectedListener
import me.androidbox.tamboon.presentation.screens.listeners.FetchCharitiesListener
import me.androidbox.tamboon.presentation.screens.listeners.SubmitDonationListener
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import timber.log.Timber
import javax.inject.Inject

class TamboonActivity : AppCompatActivity(),
    CharitySelectedListener, SubmitDonationListener, FetchCharitiesListener {

    companion object {
        const val TAMBOON_CHARITY_KEY = "tamboonCharityKey"
        const val TAMBOON_DONATION_KEY = "tamboonDonationKey"
    }

    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

    @Inject
    lateinit var loadingFragmentRouter: LoadingFragmentRouter

    @Inject
    lateinit var charitiesFragmentRouter: CharitiesFragmentRouter

    @Inject
    lateinit var successFragmentRouter: SuccessFragmentRouter

    @Inject
    lateinit var menuMenuFragmentRouter: HomeMenuFragmentRouter

    @Inject
    lateinit var donationFragmentRouter: DonationFragmentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamboon_container)
        injectDependencies()
    }

    override fun onCharitySelected(charity: Charity) {
        Timber.d(charity.name)
  //      startDonation(charity)
    }

    override fun onSubmitDonation(donation: Donation) {
   //     startLoading()
  //      tamboonViewModel.submitDonation(donation)
    }

    override fun onFetchCharities() {
     //   tamboonViewModel.getListOfCharities()
     //   startLoading()
    }

    private fun startLoading() = loadingFragmentRouter.gotoLoadingFragment()

    private fun startHomeMenu() = menuMenuFragmentRouter.gotoHomeMenuFragment()

    private fun displayListOfCharities(charityList: List<Charity>)
            = charitiesFragmentRouter.gotoCharitiesFragment(charityList)

    private fun startDonation(charity: Charity) =
        donationFragmentRouter.gotoDonationFragment(charity)

    private fun injectDependencies() {
        val tamboonActivitySubComponent = getTamboonApplicationComponent()
            .tamboonActivitySubcomponent(TamboonActivityModule(this@TamboonActivity))

        tamboonActivitySubComponent.inject(this@TamboonActivity)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (this@TamboonActivity.applicationContext as TamboonApplication).tamboonApplicationComponent
    }
}
