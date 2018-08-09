package com.xmoba.data.persistence.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by david on 9/8/18.
 */
class KeyValueStorage @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun save(key: String, value: Long) {

        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun readLong(key: String): Long {

        return sharedPreferences.getLong(key, 0)
    }

    // TODO add methods for more data types when needed
}