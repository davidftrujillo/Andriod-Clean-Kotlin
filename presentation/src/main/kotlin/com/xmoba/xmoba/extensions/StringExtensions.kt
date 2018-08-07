package com.xmoba.xmoba.extensions

/**
 * Created by david on 7/8/18.
 */
fun String.firstUppercase(): String {

    if (this.isNotEmpty()) {
        val stringBuilder = StringBuilder(this.length)
        stringBuilder.append(this.substring(0, 1).toUpperCase())
        stringBuilder.append(if (this.length > 1) this.substring(1, this.length) else "")
        return stringBuilder.toString()
    }

    return ""
}