package me.androidbox.tamboon.utils

import co.omise.android.api.RequestListener
import co.omise.android.models.Token

class CreditCardTokenFactoryImp(
    private val clientFactory: ClientFactory,
    private val cardParamFactory: CardParamFactory,
    private val onTokenRequestFailed: (Throwable) -> Unit,
    private val onTokenRequestSuccess: (Token) -> Unit
) :
    CreditCardTokenFactory {

    override fun createTokenRequest(
        name: String,
        number: String,
        expiredMonth: Int,
        expiredYear: Int,
        securityCode: String,
        publicKey: String
    ) {

        val cardParam = cardParamFactory.createCardParam(name, number, expiredMonth, expiredYear, securityCode)
        val token = Token.CreateTokenRequestBuilder(cardParam).build()
        val client = clientFactory.createClient(publicKey)

        client.send(token, object : RequestListener<Token> {
            override fun onRequestFailed(throwable: Throwable) {
                onTokenRequestFailed(throwable)
            }

            override fun onRequestSucceed(model: Token) {
                onTokenRequestSuccess(model)
            }
        })
    }
}
