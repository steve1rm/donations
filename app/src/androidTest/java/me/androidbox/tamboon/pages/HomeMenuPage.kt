package me.androidbox.tamboon.pages

import com.agoda.kakao.screen.Screen.Companion.idle
import me.androidbox.tamboon.R
import me.androidbox.tamboon.screens.HomeMenuScreen

object HomeMenuPage {

    private val homeMenuScreen: HomeMenuScreen by lazy {
        HomeMenuScreen()
    }

    fun shouldBeVisible(): HomeMenuPage = apply {
        homeMenuScreen {
            homeMenuContainer {
                isDisplayed()
            }
        }
    }

    fun shouldDisplayTitle(): HomeMenuPage = apply {
        homeMenuScreen {
            titleCharities {
                isDisplayed()
                hasText(R.string.tamboon_charities)
            }
        }
    }

    fun shouldDisplayFetchCharitiesButton(): HomeMenuPage = apply {
        homeMenuScreen {
            fetchCharities {
                isDisplayed()
                hasText(R.string.fetch_charities)
            }
        }
    }

    fun tapFetchCharities(): HomeMenuPage = apply {
        homeMenuScreen {
            fetchCharities {
                click()
            }
        }
    }

    fun waitIdle() {
        homeMenuScreen {
            idle()
        }
    }
}
