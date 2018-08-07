package com.xmoba.xmoba.view.list

import com.xmoba.xmoba.model.UserView
import com.xmoba.xmoba.view.base.BaseView

/**
 * Created by david on 7/8/18.
 */
interface UserListView: BaseView {

    fun navigateToUserDetail(user: UserView)
    fun showUsers(userViewList: List<UserView>?)
}