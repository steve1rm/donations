package me.androidbox.tamboon.pages

import androidx.test.espresso.action.GeneralLocation.VISIBLE_CENTER
import me.androidbox.tamboon.screens.DonationScreen

object DonationPage {

    private val donationScreen: DonationScreen by lazy {
        DonationScreen()
    }

    fun shouldBeVisible(): DonationPage = apply {
        donationScreen {
            container {
                isDisplayed()
            }
        }
    }

    fun shouldDisplayNameOfCharity(_charityName: String): DonationPage = apply {
        donationScreen {
            charityName.isDisplayed()
            charityName.hasText(_charityName)
        }
    }

    fun enterDonationAmount(_amount: String): DonationPage = apply {
        donationScreen {
            amount.isDisplayed()
            amount.typeText(_amount)
        }
    }

    fun tapSubmitDonation(): DonationPage = apply {
        donationScreen {
            submitDonation.isDisplayed()
            submitDonation.click(VISIBLE_CENTER)
        }
    }
}
