package me.androidbox.tamboon.presentation.screens.listeners

import me.androidbox.tamboon.data.entities.Donation

interface SubmitDonationListener {
    fun onSubmitDonation(donation: Donation)
}
