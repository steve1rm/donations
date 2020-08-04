package me.androidbox.tamboon.data.entities

import com.google.gson.annotations.SerializedName

data class DonationResult(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("error_code") val errorCode: String,
    @SerializedName("error_message") val errorMessage: String
)
