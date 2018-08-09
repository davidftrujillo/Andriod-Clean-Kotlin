package com.xmoba.xmoba.internal.di.components

import android.app.Activity
import android.content.Context
import com.xmoba.data.internal.di.modules.DatabaseModule
import com.xmoba.data.internal.di.modules.NetworkModule
import com.xmoba.data.internal.di.modules.SharedPreferencesModule
import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import com.xmoba.domain.repository.UserRepository
import com.xmoba.xmoba.internal.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by david on 6/8/18.
 *
 * A component whose lifetime is the life of the application
 */
@Singleton
@Component(modules = [(ApplicationModule::class), (NetworkModule::class), (DatabaseModule::class), (SharedPreferencesModule::class)])
interface ApplicationComponent {

    fun inject(activity: Activity)

    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun userRepository(): UserRepository
}