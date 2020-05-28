package me.androidbox.tamboon.domain.interactors

import io.reactivex.Single
import me.androidbox.tamboon.data.entities.Charities

interface RequestCharities {
    fun getCharities() : Single<Charities>
}