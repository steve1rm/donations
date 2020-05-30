package me.androidbox.tamboon.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_donation.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.di.CharitiesFragmentModule
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.di.TamboonApplicationComponent
import me.androidbox.tamboon.presentation.viewmodels.TamboonViewModel
import org.parceler.Parcels
import timber.log.Timber
import javax.inject.Inject

class DonationFragment : Fragment() {

    @Inject
    lateinit var tamboonViewModel: TamboonViewModel

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

            tamboonViewModel.registerForDonations().observe(viewLifecycleOwner, Observer { donationResult ->
                Timber.d("donation result ${donationResult.errorMessage}")
            })

            tvCharityName.text = charity.name
            btnSubmitDonation.setOnClickListener {
                tamboonViewModel.submitDonation(Donation(tvCharityName.text.toString(), "", etAmount.text.toString().toInt()))
            }
        } ?: run {
            /* Something went wrong handle case where arguments are null */
        }
    }

    private fun injectDependencies() {
        val charitiesFragmentSubcomponent = getTamboonApplicationComponent()
            .charitiesFragmentSubcomponent(CharitiesFragmentModule(this@DonationFragment))

        charitiesFragmentSubcomponent.inject(this@DonationFragment)
    }

    private fun getTamboonApplicationComponent(): TamboonApplicationComponent {
        /* Something bad happened !! we should not proceed */
        return (activity!!.application as TamboonApplication).tamboonApplicationComponent
    }
}
