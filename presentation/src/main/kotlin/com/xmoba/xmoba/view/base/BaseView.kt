package com.xmoba.xmoba.view.base

import android.content.Context

/**
 * Created by david on 7/8/18.
 */
interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun context(): Context?

    fun finishView()

    fun isSafeManipulateView(): Boolean
}