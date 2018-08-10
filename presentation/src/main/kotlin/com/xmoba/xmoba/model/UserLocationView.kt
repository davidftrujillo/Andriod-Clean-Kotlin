package com.xmoba.xmoba.model

/**
 * Created by david on 7/8/18.
 */
data class UserLocationView(
        val street: String,
        val city: String,
        val state: String,
        val postCode: String,
        val latitude: Double,
        val longitude: Double
)