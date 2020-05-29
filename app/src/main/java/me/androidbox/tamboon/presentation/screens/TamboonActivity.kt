package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import me.androidbox.tamboon.R
import me.androidbox.tamboon.di.TamboonActivityModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.routers.LoadingFragmentRouter
import timber.log.Timber
import javax.inject.Inject

class TamboonActivity : AppCompatActivity() {
    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

    @Inject
    lateinit var loadingFragmentRouter: LoadingFragmentRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamboon_container)
        injectDependencies()

        tamboonViewModel.registerForCharities().observe(this@TamboonActivity, Observer {
            Timber.d("Charities $it")

        })

        tamboonViewModel.getListOfCharities()
        startLoading()
    }

    private fun startLoading() = loadingFragmentRouter.gotoLoadingFragment()

    private fun injectDependencies() {
        val tamboonActivitySubComponent = getTamboonApplicationComponent()
            .tamboonActivitySubcomponent(TamboonActivityModule(this@TamboonActivity))

        tamboonActivitySubComponent.inject(this@TamboonActivity)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (this@TamboonActivity.applicationContext as TamboonApplication).tamboonApplicationComponent
    }
}
