package com.xmoba.data.internal.di.components

import com.xmoba.data.internal.di.modules.NetworkModule
import com.xmoba.data.repository.datasource.RemoteUserDataSource
import dagger.Component
import javax.inject.Singleton

/**
 * Created by david on 7/8/18.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetworkComponent {

    fun inject(remoteUserDataSource: RemoteUserDataSource)
}