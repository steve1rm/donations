package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import co.omise.android.api.Client
import co.omise.android.api.Request
import co.omise.android.api.RequestListener
import co.omise.android.models.CardParam
import co.omise.android.models.Token
import kotlinx.android.synthetic.main.fragment_donation.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.screens.listeners.SubmitDonationListener
import org.parceler.Parcels
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DonationFragment : Fragment() {
    @Inject
    lateinit var submitDonationListener: SubmitDonationListener

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

            val client = Client(UUID.randomUUID().toString())
            val cardParm = CardParam(
                etCardName.cardName,
                etCreditCard.cardNumber,
                etExpiryDate.expiryMonth,
                etExpiryDate.expiryYear,
                etSecurityCode.securityCode)

            val token = Token.CreateTokenRequestBuilder(cardParm).build()

            client.send(token, object: RequestListener<Token> {
                override fun onRequestFailed(throwable: Throwable) {
                    Timber.e(throwable, throwable.localizedMessage)
                }

                override fun onRequestSucceed(model: Token) {
                    Timber.d("onRequestSucceed $model")
                }
            })

            btnSubmitDonation.setOnClickListener {
                if(shouldSendDonation(token)) {
                    submitDonationListener.onSubmitDonation(
                        Donation(
                            tvCharityName.text.toString(),
                            token.toString(),
                            etAmount.text.toString().toInt()
                        )
                    )
                }
            }
        } ?: run {
            /* Something went wrong handle case where arguments are null */
        }
    }

    private fun shouldSendDonation(token: Request<Token>): Boolean {
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
