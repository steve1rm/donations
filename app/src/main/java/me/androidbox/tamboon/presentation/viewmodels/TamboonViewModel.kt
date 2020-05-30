package me.androidbox.tamboon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import timber.log.Timber

class TamboonViewModel(
    private val requestCharities: RequestCharities,
    private val requestDonation: RequestDonation)
    : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val donationData = MutableLiveData<DonationResult>()
    private val charitiesData = MutableLiveData<Charities>()

    fun getListOfCharities() {
        compositeDisposable.add(requestCharities.getCharities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { charities ->
                    Timber.d("onSuccess $charities")
                    charitiesData.postValue(charities)
                },
                onError = {
                    Timber.e(it.localizedMessage)
                }
            ))
    }

    fun submitDonation() {
        compositeDisposable.add(requestDonation.makeDonation(Donation("steve", "484858hdh4hdh", 20))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { donationResult ->
                    Timber.d("onSuccess $donationResult")
                    donationData.postValue(donationResult)
                },
                onError = {
                    Timber.e(it.localizedMessage)
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
