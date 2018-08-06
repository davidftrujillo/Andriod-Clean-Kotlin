package com.xmoba.xmoba.internal.di.components

import android.app.Activity
import android.content.Context
import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import com.xmoba.xmoba.internal.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by david on 6/8/18.
 *
 * A component whose lifetime is the life of the application
 */
@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(activity: Activity)

    fun context(): Context
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
}