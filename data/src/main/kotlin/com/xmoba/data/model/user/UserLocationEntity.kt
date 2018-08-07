package com.xmoba.data.model.user

import com.google.gson.annotations.SerializedName

/**
 * Created by david on 6/8/18.
 */
data class UserLocationEntity(
        val street: String,
        val city: String,
        val state: String,
        @SerializedName("postcode") val postCode: Int,
        val coordinates: CoordinatesEntity,
        val timezone: TimezoneEntity?
)