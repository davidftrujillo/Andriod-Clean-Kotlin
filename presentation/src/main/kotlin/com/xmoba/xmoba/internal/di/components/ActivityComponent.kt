package com.xmoba.xmoba.internal.di.components

import android.app.Activity
import android.app.Application
import com.xmoba.xmoba.internal.di.PerActivity
import com.xmoba.xmoba.internal.di.modules.ActivityModule
import dagger.Component

/**
 * Created by david on 6/8/18.
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotations {@link PerActivity}
 */
@PerActivity
@Component(dependencies = [(Application::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun activity(): Activity
}