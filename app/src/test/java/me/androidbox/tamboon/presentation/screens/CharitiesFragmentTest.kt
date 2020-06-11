package me.androidbox.tamboon.presentation.screens

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import me.androidbox.tamboon.presentation.adapter.CharitiesAdapter
import me.androidbox.tamboon.presentation.utils.DataFactory.createCharityList
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.parceler.Parcels
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class CharitiesFragmentTest {

    private val charitiesAdapter: CharitiesAdapter = mock()
    private lateinit var charitiesFragment: CharitiesFragment

    @Before
    fun setUp() {
        charitiesFragment = CharitiesFragment()
        charitiesFragment.charitiesAdapter = charitiesAdapter
    }

    @Test
    fun `should populate charities adapter`() {
        // Arrange
        val charitiesList = createCharityList(10)
        charitiesFragment.arguments = Bundle().apply {
            putParcelable("Key", Parcels.wrap(charitiesList)) }

//        doNothing().`when`(charitiesAdapter.populate(any()))

        // Act
        charitiesFragment.onViewCreated(View(ApplicationProvider.getApplicationContext()), Bundle())

        // Assert
        verify(charitiesAdapter).populate(charitiesList)
    }
}
