package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.omise.android.models.Token
import kotlinx.android.synthetic.main.fragment_donation.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.screens.listeners.SubmitDonationListener
import me.androidbox.tamboon.utils.CreditCardTokenFactory
import org.parceler.Parcels
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DonationFragment : Fragment() {
    @Inject
    lateinit var submitDonationListener: SubmitDonationListener

    @Inject
    lateinit var creditCardTokenFactory: CreditCardTokenFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_donation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val charity =
                Parcels.unwrap<Charity>(it.getParcelable(TamboonActivity.TAMBOON_DONATION_KEY))

            tvCharityName.text = charity.name

            btnSubmitDonation.setOnClickListener {
                creditCardTokenFactory.createTokenRequest(
                    etCardName.cardName,
                    etCreditCard.cardNumber,
                    etExpiryDate.expiryMonth,
                    etExpiryDate.expiryYear,
                    etSecurityCode.securityCode,
                    UUID.randomUUID().toString())
            }
        } ?: run {
            /* Something went wrong handle case where arguments are null */
        }
    }

    fun onRequestFailed(throwable: Throwable) {
        Timber.e(throwable)
        /* As I don't have a public key we will always fail.
           For testing purposes we are going to mock this data */
        submitDonationRequest("token")
    }

    fun onRequestSuccess(token: Token) {
       submitDonationRequest(token.toString())
    }

    private fun submitDonationRequest(token: String) {
        if(shouldSendDonation(token)) {
            submitDonationListener.onSubmitDonation(
                Donation(
                    tvCharityName.text.toString(),
                    token,
                    etAmount.text.toString().toInt()
                )
            )
        }
    }

    private fun shouldSendDonation(token: String): Boolean {
        return tvCharityName.text.isNotEmpty() &&
                token.toString().isNotEmpty() &&
                etAmount.text.toString().isNotEmpty()
    }

    private fun injectDependencies() {
        val charitiesFragmentSubcomponent = getTamboonApplicationComponent()
            .fragmentSubcomponent(FragmentModule(this@DonationFragment))

        charitiesFragmentSubcomponent.inject(this@DonationFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        /* Something bad happened !! we should not proceed */
        return (activity!!.application as TamboonApplication).tamboonApplicationComponent
    }
}
