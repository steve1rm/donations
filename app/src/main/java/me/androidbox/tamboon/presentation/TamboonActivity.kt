package me.androidbox.tamboon.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.tamboon.R
import me.androidbox.tamboon.di.TamboonActivityModule
import me.androidbox.tamboon.di.TamboonActivitySubComponent
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class TamboonActivity : AppCompatActivity() {

    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamboon)
        injectDependencies(buildTamboonSubComponent())

        tamboonViewModel.registerForCharities().observe(this@TamboonActivity, Observer {
            Timber.d("Charities $it")
        })

        tamboonViewModel.getListOfCharities()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun injectDependencies(tamboonActivitySubComponent: TamboonActivitySubComponent) {
        tamboonActivitySubComponent.inject(this@TamboonActivity)
    }

    private fun buildTamboonSubComponent(): TamboonActivitySubComponent {
        return getTamboonApplicationComponent()
            .tamboonActivitySubComponent(TamboonActivityModule(this@TamboonActivity))
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (this@TamboonActivity.applicationContext as TamboonApplication).tamboonApplicationComponent
    }
}
