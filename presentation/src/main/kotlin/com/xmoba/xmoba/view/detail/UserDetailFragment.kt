package com.xmoba.xmoba.view.detail

import android.os.Bundle
import android.view.View
import com.xmoba.xmoba.R
import com.xmoba.xmoba.internal.di.components.UserComponent
import com.xmoba.xmoba.presenter.BasePresenter
import com.xmoba.xmoba.presenter.UserDetailPresenter
import com.xmoba.xmoba.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_detail.*
import javax.inject.Inject

/**
 * Created by david on 8/8/18.
 */
class UserDetailFragment: BaseFragment(), UserDetailView {

    @Inject
    lateinit var presenter: UserDetailPresenter

    // ------------------------------------------------------------------------------------
    // --- Start initialization
    // ------------------------------------------------------------------------------------

    companion object {

        fun newInstance() = UserDetailFragment()
    }

    init {
        retainInstance = true
    }

    // ------------------------------------------------------------------------------------
    // --- End initialization
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start Default fragment lifecycle
    // ------------------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        getComponent(UserComponent::class.java).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        (getBaseActivity() as UserDetailActivity)?.configureToolbar(toolbar)

        initializeView()
    }

    private fun initializeView() {

        val email = arguments?.get("email") as String

        if (email != null) {
            presenter?.initialize(email)
        } else {
            finishView()
        }
    }

    // ------------------------------------------------------------------------------------
    // --- End Default fragment lifecycle
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int = R.layout.fragment_user_detail

    override fun getPresenter(): BasePresenter? = presenter

    override fun prepareView() {

        presenter?.setView(this)
    }

    // ------------------------------------------------------------------------------------
    // --- End BaseFragment overrides
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start UserDetailView overrides
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- End UserDetailView overrides
    // ------------------------------------------------------------------------------------
}