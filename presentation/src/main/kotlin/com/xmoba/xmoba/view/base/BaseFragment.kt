package com.xmoba.xmoba.view.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xmoba.xmoba.XmobaApplication
import com.xmoba.xmoba.internal.di.HasComponent
import com.xmoba.xmoba.internal.di.components.ApplicationComponent
import com.xmoba.xmoba.presenter.BasePresenter

/**
 * Created by david on 7/8/18.
 */
abstract class BaseFragment: Fragment(), BaseView {

    private var activity: BaseActivity? = null

    fun getBaseActivity() = activity
    fun getFragmentTag() = this.javaClass.simpleName

    abstract fun getFragmentLayout(): Int
    abstract fun getPresenter(): BasePresenter?
    abstract fun prepareView()

    // ------------------------------------------------------------------------------------
    // --- Start BaseView overrides
    // ------------------------------------------------------------------------------------

    override fun showLoading() {

        Log.d(getFragmentTag(), "Show loading...")
    }

    override fun hideLoading() {

        Log.d(getFragmentTag(), "Hide loading...")
    }

    override fun context(): Context? = activity

    override fun finishView() {

        getBaseActivity()?.finish()
    }

    override fun isSafeManipulateView(): Boolean {

        return isAdded && getBaseActivity() != null && !getBaseActivity()!!.isFinishing
    }

    // ------------------------------------------------------------------------------------
    // --- End BaseView overrides
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start dependency injection stuff
    // ------------------------------------------------------------------------------------

    protected fun <C> getComponent(componentType: Class<C>): C {

        return componentType.cast((getBaseActivity() as HasComponent<C>).getComponent())
    }

    protected fun getApplicationComponent(): ApplicationComponent {

        return (activity?.application as XmobaApplication).applicationComponent
    }

    // ------------------------------------------------------------------------------------
    // --- End dependency injection stuff
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start Default fragment lifecycle
    // ------------------------------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(getFragmentLayout(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        prepareView()
    }

    override fun onAttach(context: Context?) {

        super.onAttach(context)

        if (context is BaseActivity) {
            this.activity = context
        }
    }

    // ------------------------------------------------------------------------------------
    // --- End fragment lifecycle
    // ------------------------------------------------------------------------------------
}