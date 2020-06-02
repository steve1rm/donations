package me.androidbox.tamboon.automation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import me.androidbox.tamboon.R
import me.androidbox.tamboon.pages.HomeMenuPage
import me.androidbox.tamboon.presentation.screens.TamboonActivity
import me.androidbox.tamboon.rules.AndroidTestComponentRule
import me.androidbox.tamboon.utils.Untilites
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
        mockWebServer.enqueue(MockResponse().setBody(Untilites.loadFromResources("json/charities.json")))

        ActivityScenario.launch(TamboonActivity::class.java)

        HomeMenuPage
            .shouldBeVisible()
            .shouldDisplayTitle()
            .shouldDisplayFetchCharitiesButton()
            .tapFetchCharities()
    }
}
