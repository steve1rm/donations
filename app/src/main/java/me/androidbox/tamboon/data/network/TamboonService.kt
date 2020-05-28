package me.androidbox.tamboon.data.network

import io.reactivex.Single
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.entities.Donation
import me.androidbox.tamboon.data.entities.DonationResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TamboonService {
    @GET(TamboonServiceUrls.CHARITIES)
    fun getCharities(): Single<Charities>

    @POST(TamboonServiceUrls.DONATIONS)
    fun donate(@Body donation: Donation): Single<DonationResult>
}


