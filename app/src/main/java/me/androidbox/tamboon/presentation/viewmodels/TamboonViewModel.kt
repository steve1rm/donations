package me.androidbox.tamboon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.utils.SchedulerProvider
import timber.log.Timber
import java.util.concurrent.TimeUnit

class TamboonViewModel(
    private val requestCharities: RequestCharities,
    private val requestDonation: RequestDonation,
    private val uiScheduler: Scheduler,
    private val backgroundScheduler: Scheduler)
    : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val donationData = MutableLiveData<DonationResult>()
    private val charitiesData = MutableLiveData<Charities>()

    fun getListOfCharities() {
        compositeDisposable.add(requestCharities.getCharities()
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onSuccess = { charities ->
                    Timber.d("onSuccess $charities")
                    charitiesData.postValue(charities)
                },
                onError = {
                    Timber.e("failed to get list of ${it.localizedMessage}")
                    charitiesData.postValue(Charities(0, emptyList()))
                }
            ))
    }

    fun submitDonation(donation: Donation) {
        compositeDisposable.add(requestDonation.makeDonation(donation)
            .throttleFirst(1, TimeUnit.SECONDS)
            .subscribeOn(backgroundScheduler)
            .observeOn(uiScheduler)
            .subscribeBy(
                onNext = { donationResult ->
                    Timber.d("onSuccess $donationResult")
                    donationData.postValue(donationResult)
                },
                onError = {
                    Timber.e(it.localizedMessage)
                    donationData.postValue(DonationResult(false, "", ""))
                }
            ))
    }

    fun registerForCharities(): MutableLiveData<Charities> = charitiesData

    fun registerForDonations(): MutableLiveData<DonationResult> = donationData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
