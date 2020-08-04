package me.androidbox.tamboon.utils

import co.omise.android.models.CardParam
import javax.inject.Inject

class CardParamFactory @Inject constructor() {
    fun createCardParam(
        name: String,
        number: String,
        expiredMonth: Int,
        expiredYear: Int,
        securityCode: String
    ): CardParam {
        return CardParam(name, number, expiredMonth, expiredYear, securityCode)
    }
}
