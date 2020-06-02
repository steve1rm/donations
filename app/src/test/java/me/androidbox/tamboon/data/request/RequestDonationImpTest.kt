package me.androidbox.tamboon.data.request

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import me.androidbox.tamboon.data.network.TamboonService
import me.androidbox.tamboon.presentation.utils.DataFactory.createDonation
import me.androidbox.tamboon.presentation.utils.DataFactory.createDonationResult
import org.junit.Before
import org.junit.Test

class RequestDonationImpTest {

    private val tamboonService: TamboonService = mock()
    private lateinit var requestDonationImp: RequestDonationImp

    @Before
    fun setUp() {
       requestDonationImp  = RequestDonationImp(tamboonService)
    }

    @Test
    fun `should request charities`() {
        // Arrange
        val donation = createDonation()
        val donationResult = createDonationResult()
        whenever(tamboonService.donate(donation)).thenReturn(Flowable.just(donationResult))

        // Act
        val testObserver = requestDonationImp.makeDonation(donation).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(donationResult)
    }
}
