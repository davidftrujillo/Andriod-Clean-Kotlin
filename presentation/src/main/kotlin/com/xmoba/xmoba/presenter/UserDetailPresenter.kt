package com.xmoba.xmoba.presenter

import com.xmoba.xmoba.internal.di.PerActivity
import com.xmoba.xmoba.view.base.BaseView
import com.xmoba.xmoba.view.detail.UserDetailView
import javax.inject.Inject

/**
 * Created by david on 8/8/18.
 */
@PerActivity
class UserDetailPresenter @Inject constructor(): BasePresenter {

    private var userDetailView: UserDetailView? = null

    override fun getView(): BaseView? = this.userDetailView

    fun setView(view: UserDetailView) {

        this.userDetailView = view
    }

    fun initialize(email: String) {

        this.userDetailView?.showLoading()

        executeInteractor(email)
    }

    private fun executeInteractor(email: String) {

        // TODO execute use case GetUserByEmail
    }
}
