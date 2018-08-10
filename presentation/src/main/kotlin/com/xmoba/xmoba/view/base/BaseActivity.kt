package com.xmoba.xmoba.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xmoba.xmoba.R
import com.xmoba.xmoba.XmobaApplication
import com.xmoba.xmoba.internal.di.HasComponent
import com.xmoba.xmoba.internal.di.components.ApplicationComponent
import com.xmoba.xmoba.internal.di.modules.ActivityModule

/**
 * Created by david on 7/8/18.
 */
abstract class BaseActivity: AppCompatActivity() {

    protected abstract fun getInitialFragment(): BaseFragment?
    protected abstract fun initializeInjector()

    protected open fun getActivityLayout(): Int = R.layout.activity_frame

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(getActivityLayout())

        initializeInjector()

        prepareView()
    }

    fun prepareView() {

        val fragment = getInitialFragment()

        if (fragment != null) {
            val extras = intent.extras
            extras?.let {
                fragment?.arguments = extras
            }
            replaceFragment(R.id.container, fragment, fragment.getFragmentTag(), false)
        }
    }

    fun replaceFragment(container: Int, fragment: BaseFragment, tag: String, addToBackStack: Boolean) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(container, fragment, tag)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }

    fun getActivityModule(): ActivityModule {

        return ActivityModule(this)
    }

    protected fun <C> getComponent(componentType: Class<C>): C {

        return componentType.cast((this as HasComponent<C>).getComponent())
    }

    protected fun getApplicationComponent(): ApplicationComponent {

        return (application as XmobaApplication).applicationComponent
    }
}