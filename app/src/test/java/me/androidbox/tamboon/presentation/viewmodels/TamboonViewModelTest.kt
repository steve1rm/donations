package me.androidbox.tamboon.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.entities.DonationResult
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.domain.interactors.RequestDonation
import me.androidbox.tamboon.presentation.utils.DataFactory
import me.androidbox.tamboon.presentation.utils.DataFactory.createCharities
import me.androidbox.tamboon.presentation.utils.DataFactory.createDonation
import me.androidbox.tamboon.presentation.utils.DataFactory.createDonationResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.TimeUnit

class TamboonViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tamboonViewModel: TamboonViewModel
    private val requestCharities: RequestCharities = mock()
    private val requestDonation: RequestDonation = mock()
    private val testScheduler: TestScheduler = TestScheduler()
    private val charitiesObserver: Observer<Charities> = mock()
    private val donationObserver: Observer<DonationResult> = mock()

    @Before
    fun setUp() {
        tamboonViewModel = TamboonViewModel(
            requestCharities, requestDonation, testScheduler, testScheduler)

        tamboonViewModel.registerForCharities().observeForever(charitiesObserver)
        tamboonViewModel.registerForDonations().observeForever(donationObserver)
    }

    @Test
    fun `should request a list of charities`() {
        // Arrange
        val charities = createCharities(10)

        whenever(requestCharities.getCharities()).thenReturn(Single.just(charities))

        // Act
        tamboonViewModel.getListOfCharities()
        testScheduler.triggerActions()

        // Assert
        assertThat(tamboonViewModel.registerForCharities().value)
            .isEqualToComparingFieldByField(charities)
    }

    @Test
    fun `should not retrieve a charities when error`() {
        // Arrange
        val charities = createCharities(0)

        whenever(requestCharities.getCharities())
            .thenReturn(Single.error(Exception("Something bad happened")))

        // Act
        tamboonViewModel.getListOfCharities()
        testScheduler.triggerActions()

        // Assert
        assertThat(tamboonViewModel.registerForCharities().value)
            .isEqualToComparingFieldByField(charities)
    }


    @Test
    fun `should not retrieve charities after a timeout`() {
        // Arrange
        val charities = createCharities(0)

        whenever(requestCharities.getCharities())
            .thenReturn(Single.error(Exception("Timeout exception")))

        // Act
        tamboonViewModel.getListOfCharities()
        testScheduler.advanceTimeBy(3L, TimeUnit.SECONDS)

        // Assert
        assertThat(tamboonViewModel.registerForCharities().value)
            .isEqualToComparingFieldByField(charities)
    }

    @Test
    fun `should request a list of donations`() {
        // Arrange
        val donationResult = createDonationResult()
        val donation = createDonation()

        whenever(requestDonation.makeDonation(donation)).thenReturn(Flowable.just(donationResult))

        // Act
        tamboonViewModel.submitDonation(donation)
        testScheduler.triggerActions()

        // Assert
        assertThat(tamboonViewModel.registerForDonations().value)
            .isEqualToComparingFieldByField(donationResult)
    }

    @Test
    fun `should not retrieve a donations when error`() {
        // Arrange
        val donationResult = DonationResult(false, "", "")
        val donation = createDonation()

        whenever(requestDonation.makeDonation(donation))
            .thenReturn(Flowable.error(Exception("timeout exception")))

        // Act
        tamboonViewModel.submitDonation(donation)
        testScheduler.triggerActions()

        // Assert
        assertThat(tamboonViewModel.registerForDonations().value)
            .isEqualToComparingFieldByField(donationResult)
    }
}
