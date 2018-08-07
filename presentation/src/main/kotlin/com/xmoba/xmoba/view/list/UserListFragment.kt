package com.xmoba.xmoba.view.list

import android.os.Bundle
import android.util.Log
import com.xmoba.xmoba.R
import com.xmoba.xmoba.extensions.toastShort
import com.xmoba.xmoba.internal.di.components.UserComponent
import com.xmoba.xmoba.model.UserView
import com.xmoba.xmoba.presenter.BasePresenter
import com.xmoba.xmoba.presenter.UserListPresenter
import com.xmoba.xmoba.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

/**
 * Created by david on 7/8/18.
 */
class UserListFragment : BaseFragment(), UserListView {

    @Inject
    lateinit var presenter: UserListPresenter
//    @Inject
//    lateinit var adapter: UserListAdapter

    // ------------------------------------------------------------------------------------
    // --- Start initialization
    // ------------------------------------------------------------------------------------

    companion object {

        fun newInstance() = UserListFragment()
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

    // ------------------------------------------------------------------------------------
    // --- End Default fragment lifecycle
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start UserListView overrides
    // ------------------------------------------------------------------------------------

    override fun showUsers(userViewList: List<UserView>?) {

        userViewList?.forEach {
            Log.d(getFragmentTag(), it.toString())
        }
    }

    override fun navigateToUserDetail(user: UserView) {

        toastShort("${user.userName.title} ${user.userName.firstName} ${user.userName.lastName}")
    }

    // ------------------------------------------------------------------------------------
    // --- End UserListView overrides
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start BaseFragment overrides
    // ------------------------------------------------------------------------------------

    override fun getFragmentLayout(): Int = R.layout.fragment_user_list

    override fun getPresenter(): BasePresenter? {

        return presenter
    }

    override fun prepareView() {

//        setUpRecyclerView()

        presenter?.setView(this)
        presenter?.initialize()
    }

    // ------------------------------------------------------------------------------------
    // --- End BaseFragment overrides
    // ------------------------------------------------------------------------------------
}