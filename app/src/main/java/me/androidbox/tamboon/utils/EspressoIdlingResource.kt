package me.androidbox.tamboon.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL_TAMBOON"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if(!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }

}