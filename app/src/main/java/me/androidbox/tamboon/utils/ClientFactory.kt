package me.androidbox.tamboon.utils

import co.omise.android.api.Client
import javax.inject.Inject

class ClientFactory @Inject constructor(){
    fun createClient(publicKey: String): Client {
        return Client(publicKey)
    }
}
