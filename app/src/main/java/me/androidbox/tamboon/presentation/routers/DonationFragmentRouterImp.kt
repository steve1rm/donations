package me.androidbox.tamboon.presentation.routers

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.presentation.screens.DonationFragment
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import org.parceler.Parcels

class DonationFragmentRouterImp(private val fragmentManager: FragmentManager)
    : DonationFragmentRouter {

    override fun gotoDonationFragment(charity: Charity) {
        val donationFragment = DonationFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TamboonActivity.TAMBOON_DONATION_KEY, Parcels.wrap(charity))
            }
        }

        fragmentManager.run {
            val fragmentTransaction = beginTransaction()
            fragmentTransaction.replace(R.id.activity_tamboon_container, donationFragment, "donationFragment")
            fragmentTransaction.commit()
        }
    }
}
