package com.xmoba.data.persistence.database.converters

import android.arch.persistence.room.TypeConverter
import com.xmoba.data.model.user.CoordinatesEntity

/**
 * Created by david on 8/8/18.
 */
object CoordinatesConverter {

    @TypeConverter
    @JvmStatic
    fun coordinatesToString(coordinates: CoordinatesEntity): String {

        return "${coordinates.latitude},${coordinates.longitude}"
    }

    @TypeConverter
    @JvmStatic
    fun stringToCoordinates(s: String): CoordinatesEntity {

        val splitted = s.split(",")
        if (splitted != null && splitted.size == 2) {

            return CoordinatesEntity(splitted[0], splitted[1])
        }

        return CoordinatesEntity("0", "0")
    }
}