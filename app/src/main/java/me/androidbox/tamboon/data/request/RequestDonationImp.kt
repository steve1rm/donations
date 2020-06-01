package me.androidbox.tamboon.data.request

import io.reactivex.Flowable
import io.reactivex.Single
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult
import me.androidbox.tamboon.data.network.TamboonService
import me.androidbox.tamboon.domain.interactors.RequestDonation
import java.util.concurrent.TimeUnit

class RequestDonationImp(private val tamboonService: TamboonService) : RequestDonation {

    override fun makeDonation(donation: Donation): Flowable<DonationResult> {
        return tamboonService.donate(donation)
            .timeout(3, TimeUnit.SECONDS)
    }
}
