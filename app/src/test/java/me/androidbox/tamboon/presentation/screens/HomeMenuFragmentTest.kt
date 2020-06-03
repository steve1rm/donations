package me.androidbox.tamboon.presentation.screens

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.fragment_homemenu.view.*
import me.androidbox.tamboon.R
import me.androidbox.tamboon.presentation.screens.listeners.FetchCharitiesListener
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(AndroidJUnit4::class)
class HomeMenuFragmentTest {

    private val fetchCharitiesListener: FetchCharitiesListener = mock()

    private lateinit var fragment: HomeMenuFragment
    private lateinit var btnFetcharities: Button

    @Before
    fun setUp() {
        fragment = HomeMenuFragment()

        fragment.fetchCharitiesListener = fetchCharitiesListener
    }

    @Test
    fun `should fetch charities when button tapped`() {
        // Arrange
    //    fragment.onViewCreated(mock(), mock())

        // Act
        val scenario: FragmentScenario<HomeMenuFragment> = launchFragmentInContainer<HomeMenuFragment>()
        btnFetcharities = fragment.view?.findViewById(R.id.btnFetchCharities)!!
        btnFetcharities.performClick()

        // Assert
        verify(fetchCharitiesListener).onFetchCharities()
    }
}