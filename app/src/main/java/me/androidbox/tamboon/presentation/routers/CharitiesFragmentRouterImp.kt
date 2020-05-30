package me.androidbox.tamboon.presentation.routers

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import me.androidbox.tamboon.R
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.presentation.screens.CharitiesFragment
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import org.parceler.Parcels

class CharitiesFragmentRouterImp(private val fragmentManager: FragmentManager) :
    CharitiesFragmentRouter {

    override fun gotoCharitiesFragment(charityList: List<Charity>) {
        val charitiesFragment = CharitiesFragment()

        charitiesFragment.arguments = Bundle().apply {
            putParcelable(TamboonActivity.TAMBOON_CHARITY_KEY, Parcels.wrap(charityList))
        }

        fragmentManager.run {
            val fragmentTransaction = beginTransaction()
            fragmentTransaction.replace(R.id.activity_tamboon_container, charitiesFragment, "charitiesFragment")
            fragmentTransaction.commit()
        }
    }
}
