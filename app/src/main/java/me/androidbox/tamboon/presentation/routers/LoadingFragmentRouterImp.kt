package me.androidbox.tamboon.presentation.routers

import androidx.fragment.app.FragmentManager
import me.androidbox.tamboon.R
import me.androidbox.tamboon.presentation.screens.LoadingFragment

class LoadingFragmentRouterImp(private val fragmentManager: FragmentManager) :
    LoadingFragmentRouter {

    override fun gotoLoadingFragment() {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()

            fragmentTransaction.replace(R.id.activity_tamboon_container, LoadingFragment(), "LoadingFragment")
            fragmentTransaction.commit()
        }
    }
}
