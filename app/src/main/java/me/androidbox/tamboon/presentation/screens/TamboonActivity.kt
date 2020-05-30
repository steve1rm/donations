package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.di.TamboonActivityModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.routers.CharitiesFragmentRouter
import me.androidbox.tamboon.presentation.routers.LoadingFragmentRouter
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import timber.log.Timber
import javax.inject.Inject

class TamboonActivity : AppCompatActivity(), CharitySelectedListener {

    companion object {
        const val TAMBOON_CHARITY_KEY = "tamboonCharityKey"
    }

    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

    @Inject
    lateinit var loadingFragmentRouter: LoadingFragmentRouter

    @Inject
    lateinit var charitiesFragmentRouter: CharitiesFragmentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamboon_container)
        injectDependencies()

        tamboonViewModel.registerForCharities().observe(this@TamboonActivity, Observer {
            Timber.d("Charities ${it.charityList}")
            startCharities(it.charityList)
        })

        tamboonViewModel.getListOfCharities()
        startLoading()
    }

    override fun onCharitySelected(charity: Charity) {
        // Go to donation
        Timber.d(charity.name)
    }

    private fun startLoading() = loadingFragmentRouter.gotoLoadingFragment()

    private fun startCharities(charityList: List<Charity>)
            = charitiesFragmentRouter.goToCharitiesFragment(charityList)

    private fun injectDependencies() {
        val tamboonActivitySubComponent = getTamboonApplicationComponent()
            .tamboonActivitySubcomponent(TamboonActivityModule(this@TamboonActivity))

        tamboonActivitySubComponent.inject(this@TamboonActivity)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (this@TamboonActivity.applicationContext as TamboonApplication).tamboonApplicationComponent
    }
}
