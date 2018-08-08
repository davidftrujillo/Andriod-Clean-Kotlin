package com.xmoba.data.model.database.converters

import android.arch.persistence.room.TypeConverter

/**
 * Created by david on 8/8/18.
 */
object PostCodeConverter {

    @TypeConverter
    @JvmStatic
    fun anyToString(value: Any): String {

        return value as? String ?: value.toString()
    }

    @TypeConverter
    @JvmStatic
    fun stringToAny(value: String): Any {

        return value as? String ?: value.toString()
    }
}