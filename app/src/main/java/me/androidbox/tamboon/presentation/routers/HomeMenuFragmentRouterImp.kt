package me.androidbox.tamboon.presentation.routers

import androidx.fragment.app.FragmentManager
import me.androidbox.tamboon.R
import me.androidbox.tamboon.presentation.screens.HomeMenuFragment

class HomeMenuFragmentRouterImp(private val fragmentManager: FragmentManager) : HomeMenuFragmentRouter {
    override fun gotoHomeMenuFragment() {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()

            fragmentTransaction.replace(R.id.activity_tamboon_container, HomeMenuFragment(), "homeMenuFragment")
            fragmentTransaction.commit()
        }
    }
}
