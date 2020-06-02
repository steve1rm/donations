package me.androidbox.tamboon.data.request

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import me.androidbox.tamboon.data.network.TamboonService
import me.androidbox.tamboon.domain.interactors.RequestCharities
import me.androidbox.tamboon.presentation.utils.DataFactory
import me.androidbox.tamboon.presentation.utils.DataFactory.createCharities
import org.junit.Before
import org.junit.Test

class RequestCharitiesImpTest {

    private val tamboonService: TamboonService = mock()
    private lateinit var requestCharities: RequestCharities

    @Before
    fun setUp() {
        requestCharities = RequestCharitiesImp(tamboonService)
    }

    @Test
    fun `should request charities`() {
        // Arrange
        val charities = createCharities(10)
        whenever(tamboonService.getCharities()).thenReturn(Single.just(charities))

        // Act
        val testObserver = requestCharities.getCharities().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(charities)
    }
}
