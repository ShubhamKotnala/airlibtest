package com.ample.airlib.ui.login

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.ample.airlib.data.LoginDatabase
import com.ample.airlib.data.LoginRepository
import com.ample.airlib.network.RetrofitBuilder
import com.ample.airlib.utils.Resource
import com.ample.airlib.utils.Status
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest : TestCase() {

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var apiUsersObserver: Observer<Resource<Any>>
    @Mock
    private lateinit var loginRepository: LoginRepository

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @Before
    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val database by lazy { LoginDatabase.getDatabase(context) }

        loginRepository = LoginRepository(RetrofitBuilder.apiService, database.loginDao())
        loginViewModel = LoginViewModel(loginRepository)
    }

    @Test
    fun testLoginDataChangedWithEmptyUserName() {
        loginViewModel.loginDataChanged("", "password")

        loginViewModel._loginForm.getOrAwaitValue().let {
            it.status == Status.ERROR
        }
        assertThat(loginViewModel._loginForm.value?.data != Status.SUCCESS).isTrue()
        loginViewModel._loginForm.removeObserver(apiUsersObserver)
    }

    @Test
    fun testLoginDataChangedWithEmptyPassword() {
        loginViewModel.loginDataChanged("username", "")

        loginViewModel._loginForm.getOrAwaitValue().let {
            it.status == Status.ERROR
        }
        assertThat(loginViewModel._loginForm.value?.data != Status.SUCCESS).isTrue()
        loginViewModel._loginForm.removeObserver(apiUsersObserver)
    }

    @Test
    fun testLoginDataChanged() {
        loginViewModel.loginDataChanged("username", "password")

        loginViewModel._loginForm.getOrAwaitValue().let {
            it.status == Status.ERROR
        }
        assertThat(loginViewModel._loginForm.value?.data == Status.SUCCESS).isTrue()
        loginViewModel._loginForm.removeObserver(apiUsersObserver)
    }
}