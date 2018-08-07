package com.xmoba.xmoba.view.list

import com.xmoba.xmoba.internal.di.HasComponent
import com.xmoba.xmoba.internal.di.components.DaggerUserComponent
import com.xmoba.xmoba.internal.di.components.UserComponent
import com.xmoba.xmoba.view.base.BaseActivity
import com.xmoba.xmoba.view.base.BaseFragment

/**
 * Created by david on 7/8/18.
 */
class UserListActivity : BaseActivity(), HasComponent<UserComponent> {

    lateinit var userComponent: UserComponent

    override fun getInitialFragment(): BaseFragment = UserListFragment.newInstance()

    override fun initializeInjector() {

        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
    }

    override fun getComponent(): UserComponent {

        return userComponent
    }
}