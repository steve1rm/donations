package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import co.omise.android.models.Token
import java.util.UUID
import javax.inject.Inject
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.databinding.FragmentDonationBinding
import me.androidbox.tamboon.di.FragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.utils.CreditCardTokenFactory
import timber.log.Timber

class DonationFragment : Fragment() {

    @Inject
    lateinit var creditCardTokenFactory: CreditCardTokenFactory

    private lateinit var binding: FragmentDonationBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDonationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)

        arguments?.let {
            if (!it.isEmpty) {
                val charity = DonationFragmentArgs.fromBundle(it).charity

                binding.tvCharityName.text = charity.name

                binding.btnSubmitDonation.setOnClickListener {
                    creditCardTokenFactory.createTokenRequest(
                        binding.etCardName.cardName,
                        binding.etCreditCard.cardNumber,
                        binding.etExpiryDate.expiryMonth,
                        binding.etExpiryDate.expiryYear,
                        binding.etSecurityCode.securityCode,
                        UUID.randomUUID().toString()
                    )
                }
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
        if (shouldSendDonation(token)) {
            val navDirection = DonationFragmentDirections.actionDonationFragmentToLoadingFragment(
                donation = Donation(
                binding.tvCharityName.text.toString(),
                token,
                binding.etAmount.text.toString().toInt()))

            navController.navigate(navDirection)
        } else {
            Toast.makeText(activity, "Ensure all details are entered", Toast.LENGTH_LONG).show()
        }
    }

    private fun shouldSendDonation(token: String): Boolean {
        return binding.tvCharityName.text.isNotEmpty() &&
                token.isNotEmpty() &&
                binding.etAmount.text.toString().isNotEmpty()
    }

    private fun injectDependencies() {
        getTamboonApplicationComponent()
            .fragmentSubcomponent(FragmentModule(this@DonationFragment))
            .inject(this@DonationFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        return (requireActivity().application as TamboonApplication).tamboonApplicationComponent
    }
}
