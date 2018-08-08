package com.xmoba.data.model.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by david on 8/8/18.
 */
object ListStringConverter {

    @TypeConverter
    @JvmStatic
    fun saveList(list: List<String>): String {

        return Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun restoreList(s: String): List<String> {

        return Gson().fromJson<List<String>>(s)
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
}