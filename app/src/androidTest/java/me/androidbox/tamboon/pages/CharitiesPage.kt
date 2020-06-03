package me.androidbox.tamboon.pages

import androidx.test.espresso.action.GeneralLocation
import me.androidbox.tamboon.screens.CharitiesScreen
import me.androidbox.tamboon.screens.CharitiesScreen.Item

object CharitiesPage {

    private val charitiesScreen by lazy {
        CharitiesScreen()
    }

    fun shouldBeVisible(): CharitiesPage = apply {
        charitiesScreen {
            container {
                isDisplayed()
            }
        }
    }

    fun shouldHaveSize(size: Int): CharitiesPage = apply {
        charitiesScreen {
            charities {
                hasSize(size)
            }
        }
    }

    fun shouldHaveItemAtPosition(position: Int, _id: String, _name: String): CharitiesPage = apply {
        charitiesScreen {
            charities {
                childAt<Item>(position) {
                    id.hasText(_id)
                    id.isDisplayed()

                    name.hasText(_name)
                    name.isDisplayed()

                    logo.isDisplayed()
                }
            }
        }
    }

    fun tapFirstItem(): CharitiesPage = apply {
        charitiesScreen {
            charities {
                firstChild<Item> {
                    click(GeneralLocation.VISIBLE_CENTER)
                }
            }
        }
    }
}
