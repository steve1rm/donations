package me.androidbox.tamboon.data.entities

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel
data class Charity @ParcelConstructor constructor(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo_url") val logoUrl: String)
