package com.xmoba.xmoba.presenter

import com.xmoba.domain.interactor.GetUserByEmail
import com.xmoba.domain.model.User
import com.xmoba.xmoba.internal.di.PerActivity
import com.xmoba.xmoba.mapper.UserMapper
import com.xmoba.xmoba.view.base.BaseView
import com.xmoba.xmoba.view.detail.UserDetailView
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by david on 8/8/18.
 */
@PerActivity
class UserDetailPresenter @Inject constructor(
        private val getUserByEmail: GetUserByEmail,
        private val userMapper: UserMapper): BasePresenter {

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

        getUserByEmail.execute(object : DisposableObserver<User>() {

            override fun onComplete() {

            }

            override fun onNext(user: User) {

                val userView = userMapper.map(user)
                if (userView != null) {
                    userDetailView?.updateView(userView)
                } else {
                    userDetailView?.finishView()
                }
            }

            override fun onError(e: Throwable) {

                // An error occurred while retrieving user detail. Just finish the view for the moment.
                userDetailView?.finishView()
            }
        }, email)
    }
}
