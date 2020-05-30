package me.androidbox.tamboon.presentation.routers

import androidx.fragment.app.FragmentManager
import me.androidbox.tamboon.R
import me.androidbox.tamboon.presentation.screens.SuccessFragment

class SuccessFragmentRouterImp(private val fragmentManager: FragmentManager)
    : SuccessFragmentRouter {

    override fun gotoSuccessFragment() {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()

            fragmentTransaction.replace(R.id.activity_tamboon_container, SuccessFragment(), "SuccessFragment")
            fragmentTransaction.commit()
        }
    }
}
