package me.androidbox.tamboon.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TamboonService {
    @GET(TamboonServiceUrls.CHARITIES)
    fun getCharities(@Query("key") key: String)
}
