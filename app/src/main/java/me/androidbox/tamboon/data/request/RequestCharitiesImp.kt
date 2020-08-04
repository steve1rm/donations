package me.androidbox.tamboon.data.request

import io.reactivex.Single
import java.util.concurrent.TimeUnit
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.network.TamboonService
import me.androidbox.tamboon.domain.interactors.RequestCharities

class RequestCharitiesImp(private val tamboonService: TamboonService) :
    RequestCharities {

    override fun getCharities(): Single<Charities> {
        return tamboonService.getCharities()
            .timeout(30, TimeUnit.SECONDS)
    }
}
