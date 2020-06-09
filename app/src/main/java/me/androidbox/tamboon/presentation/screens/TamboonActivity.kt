package me.androidbox.tamboon.presentation.screens

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

     /*   tamboonViewModel.registerForCharities().observe(this@TamboonActivity, Observer {
            Timber.d("Charities ${it.charityList}")
            if(it.charityList.isNotEmpty()) {
       //         displayListOfCharities(it.charityList)
            }
            else {
                Toast.makeText(this@TamboonActivity, "Failed to retrieve charities", Toast.LENGTH_LONG).show()
       //         startHomeMenu()
            }
        })*/

        tamboonViewModel.registerForDonations().observe(this@TamboonActivity, Observer {
            Timber.d("DonationResult $it")
            if(it.isSuccess && it.errorCode.isNotEmpty() && it.errorMessage.isNotEmpty()) {
                successFragmentRouter.gotoSuccessFragment()
            }
            else {
                Toast.makeText(this@TamboonActivity, "Failed to submit donation", Toast.LENGTH_LONG).show()
                startHomeMenu()
            }
        })

   //     startHomeMenu()
    }

    override fun onCharitySelected(charity: Charity) {
        Timber.d(charity.name)
        startDonation(charity)
    }

    override fun onSubmitDonation(donation: Donation) {
        startLoading()
        tamboonViewModel.submitDonation(donation)
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

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        val fragment = supportFragmentManager.findFragmentById(R.id.activity_tamboon_container)

        /* We just want to finish when we are on the home screen if the user clicks back */
        if(count == 1 && !fragment?.tag.equals(HomeMenuFragment::class.simpleName)) {
            if(supportFragmentManager.findFragmentByTag(HomeMenuFragment::class.simpleName) != null) {
                tamboonViewModel.cancelRequests()
                supportFragmentManager.popBackStack()
                startHomeMenu()
            }
        }
        else {
            finish()
        }
    }
}
