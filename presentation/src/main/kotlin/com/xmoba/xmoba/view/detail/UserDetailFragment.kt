package com.xmoba.xmoba.view.detail

import android.os.Bundle
import android.view.View
import com.xmoba.xmoba.R
import com.xmoba.xmoba.extensions.firstUppercase
import com.xmoba.xmoba.extensions.loadImage
import com.xmoba.xmoba.extensions.toStringWithFormat
import com.xmoba.xmoba.internal.di.components.UserComponent
import com.xmoba.xmoba.model.UserDateView
import com.xmoba.xmoba.model.UserView
import com.xmoba.xmoba.presenter.BasePresenter
import com.xmoba.xmoba.presenter.UserDetailPresenter
import com.xmoba.xmoba.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_detail.*
import java.util.*
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

    override fun updateView(user: UserView) {

        collapsingToolbar.title = "${user.userName.firstName.firstUppercase()} ${user.userName.lastName.firstUppercase()}"
        ivUserPhoto.loadImage(user.picture.large)
        tvDetailEmail.text = user.email
        tvDetailLocation.text = "${user.location.street}, ${user.location.city} (${user.location.postCode}), ${user.location.state}"
        tvDetailPhone.text = "${user.phone} / ${user.cell}"
        updateRegistrationDate(user.registered)
        updateBirthday(user.birthday)
    }

    private fun updateRegistrationDate(registrationDate: UserDateView) {

        val registerCalendar = Calendar.getInstance()
        registerCalendar.timeInMillis = registrationDate.date
        val registerText = "Member since ${registerCalendar.toStringWithFormat("dd/MM/yyyy")}"
        tvDetailMemberSince.text = registerText
    }

    private fun updateBirthday(birthday: UserDateView) {

        val birthdayCalendar = Calendar.getInstance()
        birthdayCalendar.timeInMillis = birthday.date
        val birthdayText = "${birthdayCalendar.toStringWithFormat("dd/MM/yyyy")} (${birthday.age} years old)"
        tvDetailBirthday.text = birthdayText
    }

    // ------------------------------------------------------------------------------------
    // --- End UserDetailView overrides
    // ------------------------------------------------------------------------------------
}