package com.xmoba.xmoba.presenter

import com.xmoba.domain.interactor.GetUsers
import com.xmoba.domain.model.User
import com.xmoba.xmoba.internal.di.PerActivity
import com.xmoba.xmoba.mapper.UserMapper
import com.xmoba.xmoba.model.UserView
import com.xmoba.xmoba.view.base.BaseView
import com.xmoba.xmoba.view.list.UserListView
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
@PerActivity
class UserListPresenter @Inject constructor(
        private val getUsers: GetUsers,
        private val userMapper: UserMapper): BasePresenter {

    private var userListView: UserListView? = null
    private var currentPage: Int = 0
    private val maxPages = 10

    override fun getView(): BaseView? = this.userListView

    fun setView(view: UserListView) {

        this.userListView = view
    }

    fun initialize() {

        this.userListView?.showLoading()

        executeInteractor()
    }

    fun onUserClicked(user: UserView) {

        userListView?.navigateToUserDetail(user)
    }

    fun onLoadMoreUsers() {

        if (currentPage < maxPages) {

            executeInteractor()
        }
    }

    private fun executeInteractor() {

        getUsers.execute(object : DisposableObserver<List<User>>() {

            override fun onComplete() {

            }

            override fun onNext(userList: List<User>) {

                if (isSafeManipulateView()) {

                    val userViewList = userMapper.mapList(userList)
                    userListView?.showUsers(userViewList)
                    userListView?.hideLoading()
                }
            }

            override fun onError(e: Throwable) {

                if (isSafeManipulateView()) {

                    userListView?.hideLoading()
                }
            }
        }, ++currentPage)
    }
}