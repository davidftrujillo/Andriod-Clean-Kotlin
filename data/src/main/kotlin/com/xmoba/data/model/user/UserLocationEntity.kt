package com.xmoba.data.model.user

import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.xmoba.data.model.database.converters.CoordinatesConverter
import com.xmoba.data.model.database.converters.PostCodeConverter
import com.xmoba.data.model.database.converters.TimezoneConverter

/**
 * Created by david on 6/8/18.
 */
data class UserLocationEntity(
        val street: String,
        val city: String,
        val state: String,
        @TypeConverters(PostCodeConverter::class)
        @SerializedName("postcode") val postCode: Any,
        @TypeConverters(CoordinatesConverter::class)
        val coordinates: CoordinatesEntity,
        @TypeConverters(TimezoneConverter::class) val timezone: TimezoneEntity?
)