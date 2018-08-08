package com.xmoba.xmoba.view.detail

import com.xmoba.xmoba.model.UserView
import com.xmoba.xmoba.view.base.BaseView

/**
 * Created by david on 8/8/18.
 */
interface UserDetailView: BaseView {

    fun updateView(user: UserView)
}