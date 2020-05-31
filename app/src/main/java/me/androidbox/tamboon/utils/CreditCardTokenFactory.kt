package me.androidbox.tamboon.utils

interface CreditCardTokenFactory {
    fun createTokenRequest(name: String,
                           number: String,
                           expiredMonth: Int,
                           expiredYear: Int,
                           securityCode: String,
                           publicKey: String)
}
