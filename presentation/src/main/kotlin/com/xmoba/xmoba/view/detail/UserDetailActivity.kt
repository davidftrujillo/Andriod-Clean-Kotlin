package com.xmoba.xmoba.view.detail

import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.xmoba.xmoba.internal.di.HasComponent
import com.xmoba.xmoba.internal.di.components.DaggerUserComponent
import com.xmoba.xmoba.internal.di.components.UserComponent
import com.xmoba.xmoba.view.base.BaseActivity
import com.xmoba.xmoba.view.base.BaseFragment

/**
 * Created by david on 8/8/18.
 */
class UserDetailActivity : BaseActivity(), HasComponent<UserComponent> {

    lateinit var userComponent: UserComponent

    // ------------------------------------------------------------------------------------
    // --- Start initialization
    // ------------------------------------------------------------------------------------

    companion object {

        fun createIntent(activity: BaseActivity, email: String): Intent {

            val intent = Intent(activity, UserDetailActivity::class.java)
            intent.putExtra("email", email)
            return intent
        }
    }

    // ------------------------------------------------------------------------------------
    // --- End initialization
    // ------------------------------------------------------------------------------------

    override fun getInitialFragment(): BaseFragment? {

        return UserDetailFragment.newInstance()
    }

    override fun initializeInjector() {

        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build()
    }

    override fun getComponent(): UserComponent {

        return userComponent
    }

    fun configureToolbar(toolbar: Toolbar) {

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}