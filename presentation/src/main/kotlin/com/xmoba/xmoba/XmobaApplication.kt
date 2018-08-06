package com.xmoba.xmoba

import android.app.Application
import com.xmoba.xmoba.internal.di.components.ApplicationComponent
import com.xmoba.xmoba.internal.di.components.DaggerApplicationComponent
import com.xmoba.xmoba.internal.di.modules.ApplicationModule

/**
 * Created by david on 6/8/18.
 */
class XmobaApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {

        super.onCreate()
        this.initializeInjector()
    }

    private fun initializeInjector() {

        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}