package com.xmoba.data.internal.di.components

import com.xmoba.data.internal.di.modules.DatabaseModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by david on 8/8/18.
 */
@Singleton
@Component(modules = [(DatabaseModule::class)])
interface DatabaseComponent {
}