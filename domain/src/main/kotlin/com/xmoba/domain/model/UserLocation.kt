package com.xmoba.domain.model

/**
 * Created by david on 6/8/18.
 */
data class UserLocation(
        val street: String,
        val city: String,
        val state: String,
        val postCode: Int,
        val latitude: Double,
        val longitude: Double
)