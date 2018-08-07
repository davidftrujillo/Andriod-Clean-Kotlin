package com.xmoba.xmoba.extensions

import android.view.View

/**
 * Created by david on 7/8/18.
 */
fun View.visible() {

    this.visibility = View.VISIBLE
}

fun View.invisible() {

    this.visibility = View.INVISIBLE
}

fun View.gone() {

    this.visibility = View.GONE
}