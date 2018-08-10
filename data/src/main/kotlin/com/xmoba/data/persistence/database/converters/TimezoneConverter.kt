package com.xmoba.data.persistence.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.xmoba.data.persistence.database.converters.ListStringConverter.fromJson
import com.xmoba.data.model.user.TimezoneEntity

/**
 * Created by david on 8/8/18.
 */
object TimezoneConverter {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun timezoneToString(list: TimezoneEntity): String {

        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun stringToTimezone(s: String): TimezoneEntity {

        return gson.fromJson<TimezoneEntity>(s)
    }
}