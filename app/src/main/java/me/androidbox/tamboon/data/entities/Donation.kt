package me.androidbox.tamboon.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Donation(
    @SerializedName("name") val name: String,
    @SerializedName("token") val token: String,
    @SerializedName("amount") val amount: Int
) : Parcelable
