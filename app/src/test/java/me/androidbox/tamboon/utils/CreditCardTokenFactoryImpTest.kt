package me.androidbox.tamboon.utils

import co.omise.android.api.Client
import co.omise.android.api.Request
import co.omise.android.api.RequestListener
import co.omise.android.models.CardParam
import co.omise.android.models.Token
import com.nhaarman.mockitokotlin2.*
import me.androidbox.tamboon.presentation.utils.MockData.getInt
import me.androidbox.tamboon.presentation.utils.MockData.getString
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class CreditCardTokenFactoryImpTest {

    private val clientFactory: ClientFactory = mock()
    private val cardParamFactory: CardParamFactory = mock()
    private val tokenRequestFailed: (Throwable) -> Unit = mock()
    private val tokenRequestSuccess: (Token) -> Unit = mock()
    private val client: Client = mock()
    private val cardParam: CardParam = mock()

    private lateinit var creditCardTokenFactoryImp: CreditCardTokenFactoryImp
    private val name = getString()
    private val number = getString()
    private val expiredYear = getInt()
    private val expiredMonth = getInt()
    private val securityCode = getString()
    private val publicKey = getString()

    @Before
    fun setUp() {
        creditCardTokenFactoryImp = CreditCardTokenFactoryImp(
            clientFactory, cardParamFactory, tokenRequestFailed, tokenRequestSuccess)

        assertThat(creditCardTokenFactoryImp).isNotNull
    }

    @Test
    fun `should create client and cardParam`() {
        // Arrange
        whenever(clientFactory.createClient(publicKey)).thenReturn(client)
        whenever(cardParamFactory.createCardParam(name, number, expiredMonth, expiredYear, securityCode))
            .thenReturn(cardParam)

        // Act
        creditCardTokenFactoryImp.createTokenRequest(name, number, expiredMonth, expiredYear, securityCode, publicKey)

        // Assert
        verify(cardParamFactory).createCardParam(name, number, expiredMonth, expiredYear, securityCode)
        verify(clientFactory).createClient(publicKey)
    }

    @Test
    fun `should fail when client send is a failure`() {
        // Arrange
        whenever(clientFactory.createClient(publicKey)).thenReturn(client)
        whenever(cardParamFactory.createCardParam(name, number, expiredMonth, expiredYear, securityCode)).thenReturn(cardParam)

        doAnswer {
            val requestToken: Request<Token> = it.getArgument(0)
            val requestListener: RequestListener<Token> = it.getArgument(1)
            val exception = Exception("Request failed")

            // Act
            requestListener.onRequestFailed(exception)

            // Assert
            verify(tokenRequestFailed).invoke(exception)
            assertThat(requestToken).isInstanceOf(Request::class.java)
        }.whenever(client).send(any(), any<RequestListener<Token>>())

        // Act
        creditCardTokenFactoryImp.createTokenRequest(
            name,
            number,
            expiredMonth,
            expiredYear,
            securityCode,
            publicKey)
    }

    @Test
    fun `should succeed when client send is successful`() {
        // Arrange
        whenever(clientFactory.createClient(publicKey)).thenReturn(client)
        whenever(cardParamFactory.createCardParam(name, number, expiredMonth, expiredYear, securityCode)).thenReturn(cardParam)

        doAnswer {
            val requestToken: Request<Token> = it.getArgument(0)
            val requestListener: RequestListener<Token> = it.getArgument(1)
            val token = Token()

            // Act
            requestListener.onRequestSucceed(token)

            // Assert
            verify(tokenRequestSuccess).invoke(any())
            assertThat(requestToken).isInstanceOf(Request::class.java)
        }.whenever(client).send(any(), any<RequestListener<Token>>())

        // Act
        creditCardTokenFactoryImp.createTokenRequest(
            name,
            number,
            expiredMonth,
            expiredYear,
            securityCode,
            publicKey)
    }
}
