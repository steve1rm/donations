package me.androidbox.tamboon.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.request.RequestCharitiesImp
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.domain.interactors.RequestCharities
import timber.log.Timber
import javax.inject.Inject

class TamboonActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var requestCharities: RequestCharities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamboon)

        (applicationContext as TamboonApplication)
            .tamboonApplicationComponent
            .inject(this)

        compositeDisposable.add(requestCharities.getCharities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    Timber.d("onSuccess $it")
                },
                onError = {
                    Timber.e(it.localizedMessage)
                }
            ))
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
