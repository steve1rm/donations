package me.androidbox.tamboon.automation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.platform.app.InstrumentationRegistry
import me.androidbox.tamboon.pages.CharitiesPage
import me.androidbox.tamboon.pages.DonationPage
import me.androidbox.tamboon.pages.HomeMenuPage
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import me.androidbox.tamboon.rules.AndroidTestComponentRule
import me.androidbox.tamboon.utils.Utilites
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

class TamboonActivityAndroidTest {
    private val androidTestComponentRule =
        AndroidTestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main = IntentsTestRule(TamboonActivity::class.java, false, false)

    private val mockWebServer: MockWebServer by lazy {
        MockWebServer()
    }

    @get:Rule
    val chain = RuleChain.outerRule(androidTestComponentRule).around(main)

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testMainLoginIsDisplayed() {
        mockWebServer.enqueue(MockResponse().setBody(Utilites.loadFromResources("json/charities.json")))
        mockWebServer.enqueue(MockResponse().setBody(Utilites.loadFromResources("json/donation.json")))

        ActivityScenario.launch(TamboonActivity::class.java)

        HomeMenuPage
            .shouldBeVisible()
            .shouldDisplayTitle()
            .shouldDisplayFetchCharitiesButton()
            .tapFetchCharities()

        CharitiesPage
            .shouldBeVisible()
            .shouldHaveSize(4)
            .shouldHaveItemAtPosition(0, "47464", "Ban Khru Noi")
            .shouldHaveItemAtPosition(1, "87454343", "Habitat for Humanity Thailand")
            .shouldHaveItemAtPosition(2, "676328", "Paper Ranger")
            .shouldHaveItemAtPosition(3, "3875", "Makhampom")
            .tapFirstItem()

        DonationPage
            .shouldBeVisible()
            .shouldDisplayNameOfCharity("Ban Khru Noi")
            .enterDonationAmount("55555")
            .tapSubmitDonation()
    }

    @Test
    fun shouldNotDisplayWhenFetchingFailsWithA404() {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        ActivityScenario.launch(TamboonActivity::class.java)

        HomeMenuPage
            .shouldBeVisible()
            .shouldDisplayTitle()
            .shouldDisplayFetchCharitiesButton()
            .tapFetchCharities()
    }
}
