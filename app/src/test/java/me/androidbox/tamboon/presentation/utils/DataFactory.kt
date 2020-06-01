package me.androidbox.tamboon.presentation.utils

import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.entities.Charity
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult
import me.androidbox.tamboon.presentation.utils.MockData.getBoolean
import me.androidbox.tamboon.presentation.utils.MockData.getInt
import me.androidbox.tamboon.presentation.utils.MockData.getString

object DataFactory {

    fun createCharities(count: Int): Charities =
        Charities(count, createCharityList(count))

    fun createCharityList(count: Int): List<Charity> {
        val charityList = mutableListOf<Charity>()

        repeat(count) {
            charityList.add(createCharity())
        }

        return charityList
    }

    fun createCharity(): Charity =
        Charity(
            getInt(),
            getString(),
            getString())

    fun createDonationResult(): DonationResult =
        DonationResult(
            getBoolean(),
            getString(),
            getString())

    fun createDonation(): Donation =
        Donation(
            getString(),
            getString(),
            getInt())
}
