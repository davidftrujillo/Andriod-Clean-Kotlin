package com.xmoba.xmoba.internal.di.modules

import android.app.Activity
import com.xmoba.xmoba.internal.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by david on 6/8/18.
 *
 * A module to wrap the Activity state and expose it to the graph
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    fun activity(): Activity {
        return this.activity
    }
}