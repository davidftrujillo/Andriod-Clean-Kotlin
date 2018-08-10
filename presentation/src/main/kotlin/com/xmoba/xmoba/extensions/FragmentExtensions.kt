package com.xmoba.xmoba.extensions

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by david on 7/8/18.
 */
fun Fragment.toastShort(message: String) {

    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(message: String) {

    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
}