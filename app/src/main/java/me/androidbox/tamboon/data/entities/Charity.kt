package me.androidbox.tamboon.data.entities

import com.google.gson.annotations.SerializedName

data class Charity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("logo_url") val logoUrl: String)