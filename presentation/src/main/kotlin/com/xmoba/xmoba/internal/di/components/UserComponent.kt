package com.xmoba.xmoba.internal.di.components

import com.xmoba.xmoba.internal.di.PerActivity
import com.xmoba.xmoba.internal.di.modules.ActivityModule
import com.xmoba.xmoba.internal.di.modules.UserModule
import com.xmoba.xmoba.view.list.UserListFragment
import dagger.Component

/**
 * Created by david on 7/8/18.
 */
@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class), (UserModule::class)])
interface UserComponent: ActivityComponent {

    fun inject(userListFragment: UserListFragment)

//    fun inject(userDetailFragment: UserDetailFragment)
}