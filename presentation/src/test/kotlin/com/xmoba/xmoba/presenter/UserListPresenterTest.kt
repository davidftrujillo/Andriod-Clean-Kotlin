package com.xmoba.xmoba.presenter

import com.nhaarman.mockito_kotlin.*
import com.xmoba.domain.interactor.GetUsers
import com.xmoba.domain.model.*
import com.xmoba.xmoba.mapper.*
import com.xmoba.xmoba.model.UserView
import com.xmoba.xmoba.view.list.UserListView
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
class UserListPresenterTest {

    lateinit var presenter : UserListPresenter
    val getUsers : GetUsers = mock()
    lateinit var userMapper : UserMapper
    val userListView : UserListView = mock()

    @Before
    fun setUp() {

        userMapper = UserMapper(UserDateMapper(), UserNameMapper(), UserLocationMapper(), UserPictureMapper())
        presenter = UserListPresenter(getUsers, userMapper)
        presenter.setView(userListView)
        Mockito.`when`(userListView.isSafeManipulateView()).thenReturn(true)
    }

    @Test
    fun whenSetView_ThenReturnCorrectObject() {

        assertEquals(userListView, presenter.getView())
    }

    @Test
    fun whenGetUsersSuccess_ThenShowUsers() {

        val captor = argumentCaptor<DisposableObserver<List<User>>>()
        val productList = getUserList()

        presenter.initialize()
        verify(userListView, times(1)).showLoading()

        captor.apply {
            verify(getUsers, times(1)).execute(capture(), eq(1))
            firstValue.onNext(productList)
        }

        val listCaptor = argumentCaptor<List<UserView>>()
        listCaptor.apply {
            verify(userListView, times(1)).showUsers(capture())
            assertEquals(firstValue.size, productList.size)
            verify(userListView, times(1)).hideLoading()
        }
    }

    @Test
    fun whenGetUsersFails_ThenHideLoading() {

        val captor = argumentCaptor<DisposableObserver<List<User>>>()

        presenter.initialize()
        verify(userListView, times(1)).showLoading()

        captor.apply {
            verify(getUsers, times(1)).execute(capture(), eq(1))
            firstValue.onError(Throwable())
            verify(userListView, times(1)).hideLoading()
        }
    }

    @Test
    fun givenLoadedList_WhenUserClicked_ThenNavigateToUserDetail() {

        val user = userMapper.map(getUserList()[1])
        presenter.onUserClicked(user)

        verify(userListView, times(1)).navigateToUserDetail(user)
    }

    private fun getUserList(): List<User> {

        val p1 = createUser()

        return listOf(p1, p1, p1)
    }

    private fun createUser(): User {
        return User("1234567890", "male", "m1@mail.com", "999", "666",
                "ES", UserPicture("large.jpg", "medium.jpg", "thumbnail.jpg"),
                UserName("sir", "name", "last"), UserDate(98765, 5),
                UserDate(977754, 4), UserLocation("street", "city", "state", "08210", 35.111, -2.666))
    }
}