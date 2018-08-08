package com.xmoba.xmoba.view.list

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.xmoba.xmoba.R
import com.xmoba.xmoba.extensions.gone
import com.xmoba.xmoba.extensions.toastShort
import com.xmoba.xmoba.extensions.visible
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
    @Inject
    lateinit var adapter: UserListAdapter

    private var isLoading = false

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

        userViewList?.let {

            this.adapter.addUsers(userViewList)
        }

        isLoading = false
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

        setupRecyclerView()

        presenter?.setView(this)
        presenter?.initialize()
    }

    override fun showLoading() {

        super.showLoading()

        pbLoading.visible()
    }

    override fun hideLoading() {

        super.hideLoading()

        pbLoading.gone()
    }

    // ------------------------------------------------------------------------------------
    // --- End BaseFragment overrides
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start private methods
    // ------------------------------------------------------------------------------------

    private fun setupRecyclerView() {

        rvUsers.layoutManager = LinearLayoutManager(context())
        rvUsers.adapter = adapter

        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                val layoutManager = rvUsers.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= 10) {

                    isLoading = true
                    presenter?.onLoadMoreUsers()
                }
            }
        })

        this.adapter.setOnUserClickListener(object : UserListClickListener {
            override fun onUserClicked(user: UserView) {

                presenter?.onUserClicked(user)
            }
        })
    }

    // ------------------------------------------------------------------------------------
    // --- End private methods
    // ------------------------------------------------------------------------------------
}