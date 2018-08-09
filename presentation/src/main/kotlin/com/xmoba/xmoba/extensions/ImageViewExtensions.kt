package com.xmoba.xmoba.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.xmoba.xmoba.R

/**
 * Created by david on 7/8/18.
 */
fun ImageView.loadImage(url: String) {

    Picasso.get()
            .load(url)
            .error(R.drawable.x_logo)
            .placeholder(R.drawable.x_logo)
            .into(this)
}