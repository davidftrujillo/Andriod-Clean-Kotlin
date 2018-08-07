package com.xmoba.xmoba.presenter

import com.xmoba.xmoba.view.base.BaseView

/**
 * Created by david on 7/8/18.
 */
interface BasePresenter {

    /**
     * Returns the view interface for the presenter
     */
    fun getView(): BaseView?

    /**
     * Returns true if is safe to operate into the view after async operations
     */
    fun isSafeManipulateView(): Boolean {

        return getView() != null && getView()?.isSafeManipulateView()!!.or(false)
    }
}