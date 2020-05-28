package me.androidbox.tamboon.data.entities

import com.google.gson.annotations.SerializedName

data class Donation(
    @SerializedName("name") val name: String,
    @SerializedName("token") val token: String,
    @SerializedName("amount") val amount: Int)