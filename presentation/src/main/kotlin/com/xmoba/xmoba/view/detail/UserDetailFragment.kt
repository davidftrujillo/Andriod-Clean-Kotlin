package com.xmoba.xmoba.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.xmoba.xmoba.R
import com.xmoba.xmoba.extensions.firstUppercase
import com.xmoba.xmoba.extensions.loadImage
import com.xmoba.xmoba.extensions.toStringWithFormat
import com.xmoba.xmoba.internal.di.components.UserComponent
import com.xmoba.xmoba.model.UserDateView
import com.xmoba.xmoba.model.UserLocationView
import com.xmoba.xmoba.model.UserNameView
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
class UserDetailFragment : BaseFragment(), UserDetailView, OnMapReadyCallback {

    @Inject
    lateinit var presenter: UserDetailPresenter

    var mapView: MapView? = null
    var googleMap: GoogleMap? = null

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = super.onCreateView(inflater, container, savedInstanceState)
        mapView = view?.findViewById(R.id.mapDetaiLocation) as MapView
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)

        mapView?.onSaveInstanceState(outState)
    }

    override fun onResume() {

        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {

        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {

        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {

        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {

        super.onLowMemory()
        mapView?.onLowMemory()
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

        updateLocation(user.userName, user.location)
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

    private fun updateLocation(userName: UserNameView, location: UserLocationView) {

        googleMap ?: return

        val latLng = LatLng(location.latitude, location.longitude)
        with(googleMap!!) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
            addMarker(MarkerOptions().position(latLng).title("${userName.firstName}".firstUppercase()))
        }
    }

    // ------------------------------------------------------------------------------------
    // --- End UserDetailView overrides
    // ------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------
    // --- Start maps related stuff
    // ------------------------------------------------------------------------------------

    override fun onMapReady(map: GoogleMap?) {

        map ?: return
        with(map) {
            googleMap = map
            uiSettings.isZoomControlsEnabled = false
            uiSettings.isCompassEnabled = false
            uiSettings.isMyLocationButtonEnabled = false
            uiSettings.isZoomGesturesEnabled = false
            uiSettings.isRotateGesturesEnabled = false
            uiSettings.isScrollGesturesEnabled = false

            addMarker(MarkerOptions().position(LatLng(-33.862, 151.21)).title("User name"))
        }
    }

    // ------------------------------------------------------------------------------------
    // --- End maps related stuff
    // ------------------------------------------------------------------------------------
}