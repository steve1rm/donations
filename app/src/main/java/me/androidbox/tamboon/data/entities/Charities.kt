package me.androidbox.tamboon.data.entities

import com.google.gson.annotations.SerializedName

data class Charities(
    @SerializedName("total") val total: Int,
    @SerializedName("data") val charityList: List<Charity>)
