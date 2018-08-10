package com.xmoba.xmoba.presenter

import com.nhaarman.mockito_kotlin.*
import com.xmoba.domain.interactor.GetUserByEmail
import com.xmoba.domain.model.*
import com.xmoba.xmoba.mapper.*
import com.xmoba.xmoba.view.detail.UserDetailView
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

/**
 * Created by david on 10/8/18.
 */
@RunWith(MockitoJUnitRunner::class)
class UserDetailPresenterTest {

    lateinit var presenter : UserDetailPresenter
    lateinit var userMapper: UserMapper
    val getUserByEmail : GetUserByEmail = mock()
    val detailView: UserDetailView = mock()

    @Before
    fun setUp() {

        userMapper = UserMapper(UserDateMapper(), UserNameMapper(), UserLocationMapper(), UserPictureMapper())
        presenter = UserDetailPresenter(getUserByEmail, userMapper)
        presenter.setView(detailView)
    }

    @Test
    fun whenSetView_ThenReturnCorrectObject() {

        assertEquals(detailView, presenter.getView())
    }

    @Test
    fun whenGetUserSuccess_ThenUpdateView() {

        presenter.initialize("email@email.com")

        val captor = argumentCaptor<DisposableObserver<User>>()
        val user = createUser()

        captor.apply {
            verify(getUserByEmail, times(1)).execute(capture(), eq("email@email.com"))
            firstValue.onNext(user)

            verify(detailView, times(1)).updateView(any())
        }
    }

    @Test
    fun whenGetUserSuccessButMapperFails_ThenFinishView() {

        val user = createUser()
        val mockMapper : UserMapper = mock()
        Mockito.`when`(mockMapper.map(user)).thenReturn(null)

        presenter = UserDetailPresenter(getUserByEmail, mockMapper)
        presenter.setView(detailView)
        presenter.initialize("email@email.com")

        val captor = argumentCaptor<DisposableObserver<User>>()

        captor.apply {
            verify(getUserByEmail, times(1)).execute(capture(), eq("email@email.com"))
            firstValue.onNext(user)

            verify(detailView, times(1)).finishView()
        }
    }

    @Test
    fun whenGetUserFails_ThenFinishView() {

        presenter.initialize("email@email.com")

        val captor = argumentCaptor<DisposableObserver<User>>()

        captor.apply {
            verify(getUserByEmail, times(1)).execute(capture(), eq("email@email.com"))
            firstValue.onError(Throwable())

            verify(detailView, times(1)).finishView()
        }
    }

    private fun createUser(): User {

        return User("1234567890", "male", "m1@mail.com", "999", "666",
                "ES", UserPicture("large.jpg", "medium.jpg", "thumbnail.jpg"),
                UserName("sir", "name", "last"), UserDate(98765, 5),
                UserDate(977754, 4), UserLocation("street", "city", "state", "08210", 35.111, -2.666))
    }
}