package me.androidbox.tamboon.pages

import me.androidbox.tamboon.screens.SuccessScreen

object SuccessPage {

    private val successScreen: SuccessScreen by lazy {
        SuccessScreen()
    }

    fun shouldBeVisible(): SuccessPage = apply {
        successScreen {
            container {
                isDisplayed()
            }
        }
    }
}
