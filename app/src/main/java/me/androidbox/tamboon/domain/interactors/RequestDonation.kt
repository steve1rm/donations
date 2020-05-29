package me.androidbox.tamboon.domain.interactors

import io.reactivex.Single
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult

interface RequestDonation {
    fun makeDonation(donation: Donation): Single<DonationResult>
}