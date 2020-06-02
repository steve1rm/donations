package me.androidbox.tamboon.presentation.screens

import android.os.Build
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import me.androidbox.tamboon.di.TamboonApplication
import me.androidbox.tamboon.presentation.di.DaggerTestTamboonApplicationComponent
import me.androidbox.tamboon.presentation.di.TestTamboonApplicationModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class TamboonActivityTest {
    private lateinit var activityScenario: ActivityScenario<TamboonActivity>

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext as TamboonApplication

/*

        val testComponent = DaggerTestTamboonApplicationComponent
            .builder()
            .testTamboonApplicationModule(TestTamboonApplicationModule())
            .build()

        app.tamboonApplicationComponent = testComponent
        testComponent.inject(this)
*/

        activityScenario = ActivityScenario.launch(TamboonActivity::class.java)
    }

    @Test
    fun `should go to the home menu`() {
        // Act
        activityScenario.moveToState(Lifecycle.State.CREATED)

        // Assert
        activityScenario.onActivity { activity: TamboonActivity ->
            verify(activity.menuMenuFragmentRouter).gotoHomeMenuFragment()
        }
    }
}
