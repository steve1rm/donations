package me.androidbox.tamboon.data.request

import io.reactivex.Single
import me.androidbox.tamboon.data.entities.Charities
import me.androidbox.tamboon.data.network.TamboonService
import me.androidbox.tamboon.domain.interactors.RequestCharities
import javax.inject.Inject

class RequestCharitiesImp @Inject constructor(private val tamboonService: TamboonService)
    : RequestCharities {

    override fun getCharities(): Single<Charities> {
        return tamboonService.getCharities()
    }
}