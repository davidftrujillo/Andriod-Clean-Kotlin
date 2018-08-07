package com.xmoba.data.model.user

/**
 * Created by david on 6/8/18.
 */
data class UserLocationEntity(
        val street: String,
        val city: String,
        val state: String,
        val postCode: Int,
        val coordinates: CoordinatesEntity,
        val timezone: TimezoneEntity?
)