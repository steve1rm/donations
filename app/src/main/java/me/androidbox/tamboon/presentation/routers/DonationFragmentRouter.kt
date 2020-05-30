package me.androidbox.tamboon.presentation.routers

import me.androidbox.tamboon.data.entities.Charity

interface DonationFragmentRouter {
    fun gotoDonationFragment(charity: Charity)
}
