package me.androidbox.tamboon.domain.interactors

import io.reactivex.Flowable
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult

interface RequestDonation {
    fun makeDonation(donation: Donation): Flowable<DonationResult>
}
