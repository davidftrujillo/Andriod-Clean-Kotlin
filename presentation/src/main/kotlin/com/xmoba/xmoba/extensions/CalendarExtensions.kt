package com.xmoba.xmoba.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by david on 8/8/18.
 */
fun Calendar.toStringWithFormat(format: String): String {

    val formatter = SimpleDateFormat(format)
    return formatter.format(this.time)
}